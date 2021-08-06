const monthNames = ["Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
	"Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"];
let siteCore = siteNavigator(); //site
displayCategories();
daysList();
displayItems();
mostPopularItems(0);

function siteNavigator(){
	let d = new Date();
	let month = d.getMonth();
	let year = d.getFullYear();
//initialize the site: fetch categories, display items for current month, and define all the years in a list	
	(async() => {
		let response = await fetch("http://localhost:8083/items/years", {
		method: "GET"})
		let years = await response.json();
		let html = '<option value="0">Весь час</option>';
		years.forEach(year =>{
			html += '<option value="' + year + '">' + year + '</option>'
		})
			document.getElementById("yearGraph").innerHTML = html;
	})();
	return {
		getMonth: () => month,
		getYear: () => year,
		monthBack: () => {
			month--;
			if (month < 0) {
				year--;
				month = 11;
			}
			if (month != d.getMonth() && year != d.getYear()) {
				if(document.getElementById("forward").className != "move"){
					document.getElementById("forward").className = "move";
				}
			}
			displayItems();
			daysList();
				},
		monthForward: () => {
			if (month != d.getMonth() || year != d.getFullYear()) {
				month++;
				if(month == d.getMonth() && year == d.getFullYear()){
					document.getElementById("forward").className = "disabled";
				}
					if (month > 11) {
						year++;
						month = 0;
				} 		
			displayItems();
			daysList();	
			}
		}
	}
}

async function deleteItem(itemId) {
	let response = await fetch("http://localhost:8083/items/" + itemId, {
		method: "DELETE"	 	  	
    });
	displayItems();
}

function daysList() {
	let y = siteCore.getYear();
	let m = siteCore.getMonth() + 1;
	let daysInMonth = /8|3|5|10/.test(--m)?30:m==1?(!(y%4)&&y%100)||!(y%400)?29:28:31;
	let html = '<option value="">День:</option>';
	for (let i = 1; i <= daysInMonth; i++) {
		html = html + '<option value="' + i + '">' + i + '</option>';
	}
	document.getElementById("days").innerHTML = html;
};

function formatDate(date) {
	var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();
	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;
	return d;
}

async function addItem() {
	// fetch all entries from the form and check for null
	let itemName = document.getElementById("item_name").value;
	if (!itemName) {
  	    alert("Введіть назву");
	    return false;
 	 }
	let itemPrice = document.getElementById("price").value;
	if(isNaN(itemPrice) || !itemPrice){
		alert("Не коректна ціна!");
		return false;
	}	
	if(!document.getElementById("days").value){
		alert("Введіть день!");
		return false;
	}
	let itemDate = new Date(siteCore.getYear(), siteCore.getMonth(),
	document.getElementById("days").value);
	if(!document.getElementById("categories").value){
		alert("Виберіть категорію!");
		return false;
	}
	let itemCategory = await getCategoryById(document.getElementById("categories").value);
	const  response = await fetch("http://localhost:8083/items", {
		method: "POST",
		body: JSON.stringify({ name: itemName, price: itemPrice, category: itemCategory, date: itemDate }),
		headers: {
 	     'Content-Type': 'application/json'
  	  	}  	  	
    });
	document.getElementById('decor').style.display = 'none';
	displayItems();
	return await response.json();
}

async function mostPopularItems(catId){	
	let url = "http://localhost:8083/items/popular?categoryId=" + catId;
	if(catId == 0){
		url = "http://localhost:8083/items/popular";
	}
	let response = await fetch(url , {
		method: "GET"});
	let items = await response.json();
	let html = '';
	for(let i = 0; i < items.length; i++){
		let item = items[i];
		html = html + '<tr><td>' + (i + 1) + '</td>\n' +
		'        <td>' + item.name + '</td>\n' +
		'        <td>' + item.value + '</td>\n' +
		'        <td>' + item.total.toFixed(2) + '</td>\n' +
		'    </tr>';
	}	
	document.getElementById("mostPopular").getElementsByTagName("tbody")[0].innerHTML = html;
}

async function displayItems(){
	//display all items for current date
	let itemResponse = await fetch("http://localhost:8083/items" + "?year=" + siteCore.getYear() +
			 "&month=" + siteCore.getMonth(), {
		method: "GET"});
	let items = await itemResponse.json();
	let itemHtml ='';
	items.forEach ((item, index) => {
		console.log(item);
		itemHtml += '<tr><td>' + (index + 1) + '</td>\n' +
		'        <td>' + item.name + '</td>\n' +
		'        <td>' + item.price + '</td>\n' +
		'        <td>' + item.category.name + '</td>' +
		'        <td>' + new Date(item.date).getDate() + '</td>' +
		'        <td><button onclick="deleteItem(' + item.id + ')">Видалити</button></td></tr>';
		});
	document.getElementById("itemsList").getElementsByTagName("tbody")[0].innerHTML = (itemHtml);
	
	//display category count
	let categories = await getAllCategories();
	let categoryTable = '';
	categories.forEach(category => {
		categoryTable += '      <tr><td>' + category.name + '</td>' +
		'	 <td>' + items.filter(item => item.category.id == category.id).length +
		'</td></tr>';
		}
	);	
	
	//sort items	
	let resort = true;
    $("#itemsList").trigger("update", [resort]);
	
	// add html
	document.getElementById("categoryList").getElementsByTagName("tbody")[0].innerHTML = categoryTable;
	document.getElementById("current_date").innerHTML = monthNames[siteCore.getMonth()] +
	 ' ' + siteCore.getYear();
	document.getElementById("total_price").innerHTML = "Загальні витрати за місяць: " + 
	items.reduce((value, item) => value + item.price, 0).toFixed(2) + "грн";			
}

async function displayCategories() {
	let html = '<option value="">Виберіть категорію:</option>';
	let categories = await getAllCategories();
	categories.forEach(category => {
		html = html + '<option value="' + category.id + '">' + category.name + '</option>';	
	});
	document.getElementById("categoriesGraph").innerHTML = '<option value="0">Всі витрати</option>' + html.slice(45);
	document.getElementById("categories").innerHTML = html;
}

async function getAllCategories(){
	let response = await fetch("http://localhost:8083/categories", {
		method: "GET"});
	return await response.json();
}

async function getCategoryById(catId){
	let response = await fetch("http://localhost:8083/categories/" + catId, {
		method: "GET"});
	return await response.json();
}

let ctx = document.getElementById('monthStatistic').getContext('2d');
let monthChart = new Chart(ctx, {
    type: 'bar',
    data: {
        datasets: [{
            label: 'Витрати',
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

let ctx2 = document.getElementById('categoryStatistic').getContext('2d');
let categoryDounut = new Chart(ctx2, {
    type: 'doughnut',
    data: {
		labels: [],
        datasets: [{
            label: 'Витрати',
			data: [],
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
		hoverOffset: 4    
        }]
    },
})

async function updateGraph(){
	let url = "http://localhost:8083/categories/cost?year=" + document.getElementById("yearGraph").value;
	if(document.getElementById("yearGraph").value == 0){
		url = "http://localhost:8083/categories/cost";
	}
	let response = await fetch(url, {
		method: "GET"});
	let chartData = await response.json();
	updateChart(categoryDounut,chartData);
}

function updateChart(chart,chartData){
	chart.data.labels = [];
	chart.data.datasets[0].data = [];
	chartData.forEach(entry => {
		chart.data.labels.push(entry.category_Name);
		chart.data.datasets[0].data.push(entry.total);
	});
	chart.update();
}
updateChart();

//async function updateDoughnut(){
//	
//	document.getElementById("yearGraph")
//	let response = await fetch("http://localhost:8083/items/statistics", {
//		method: "GET"});
//	let chartData = await response.json();
//}


$(function() {
  $("#itemsList").tablesorter();
});