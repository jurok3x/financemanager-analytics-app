const monthNames = ["Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
	"Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"];
let navigator = siteNavigator();
drawGraph(0);


function siteNavigator(){
	let items;
	let categories;
	let d = new Date();
	let month = d.getMonth();
	let year = d.getFullYear();
	function daysList() {
		let y = year;
		let m = month + 1;
		let daysInMonth = /8|3|5|10/.test(--m)?30:m==1?(!(y%4)&&y%100)||!(y%400)?29:28:31;
		let html = '<option value="">День:</option>';
		for (let i = 1; i <= daysInMonth; i++) {
			html = html + '<option value="' + i + '">' + i + '</option>';
		}
		document.getElementById("days").innerHTML = html;
	};
	(async() => {
		await doGet("http://localhost:8083/items" + "?year=" + year +
		 "&month=" + month, (obj) => {items = obj});
		await doGet("http://localhost:8083/categories", (obj) => {categories = obj});
		await doGet("http://localhost:8083/items" + "?sortBy=date", (obj) => {
			let html;
			for(let i = 0; i <= (d.getFullYear() - (obj[0].date.slice(0, 4) / 1)); i++){
			html += '<option value="' + (d.getFullYear() - i) + '">' + (d.getFullYear() - i) + '</option>';
		}
		document.getElementById("yearGraph").innerHTML = html;
		});
		loadCategories();
		daysList();
		displayItems();
		doGet('http://localhost:8083/items/popular' + '?categoryId=' + 0, mostPopularItems);
	})();
	return {
		getItems: () => items,
		setItems: (obj) => {items = obj},
		getCategories: () => categories,
		getMonth: () => month,
		getYear: () => year,
		monthBack: async() => {
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
			await doGet("http://localhost:8083/items" + "?year=" + year +
			 "&month=" + month, (obj) => {items = obj});
			displayItems(() => (a, b) =>  a.date - b.date);
			daysList();
				},
		monthForward: async() => {
			if (month != d.getMonth() || year != d.getFullYear()) {
				month++;
				if(month == d.getMonth() && year == d.getFullYear()){
					document.getElementById("forward").className = "disabled";
				}
					if (month > 11) {
						year++;
						month = 0;
				} 		
			await doGet("http://localhost:8083/items" + "?year=" + year +
			 "&month=" + month, (obj) => {items = obj});
			displayItems(() => (a, b) =>  a.date - b.date);
			daysList();	
			}
		}
	}
}

function drawGraph() {
	let id = document.getElementById("categoriesGraph").value;
	let year = document.getElementById("yearGraph").value; 
	if(this.year == ''){this.year = d.getFullYear()};
	var xhttp = new XMLHttpRequest();
	var http = (id != 0)? 'http://localhost:8083/items/category/' + id + '?year=' + year :
	'http://localhost:8083/items' + "?year=" + year;
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			let items = JSON.parse(this.responseText);		
			let data = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			for (var i = 0; i < items.length; i++){
				let item = items[i];	
				data[(formatDate(item.date).substring(5,7) / 1) - 1] += item.price;
			}
			//Graph
			let canvas = document.getElementById('canvas');
			let ctx = canvas.getContext('2d');
			var koef = (canvas.height - 50) / Math.max.apply(null, data);
			var scaleLength = 50 / koef;
			ctx.clearRect(0, 0, canvas.width, canvas.height);
			ctx.fillStyle = "black"; // line color 
			ctx.lineWidth = 2.0; // line width
			ctx.beginPath(); 
			ctx.moveTo(30, 10); 
			ctx.lineTo(30, (canvas.height - 40)); 
			ctx.lineTo(500, (canvas.height - 40));
			ctx.stroke(); 
			//y
			ctx.fillStyle = "black";
			for(let i = 0; i < 10; i++) { 
			    ctx.fillText(Math.floor((9 - i) * scaleLength ) + "грн.", 2, i * 50 + 8); 
			    ctx.beginPath(); 
			    ctx.moveTo(25, i * 50 + 10); 
			    ctx.lineTo(30, i * 50 + 10); 
			    ctx.stroke(); 
			}
			//x
			for(var i=0; i<12; i++) { 
			    ctx.fillText(monthNames[i].substring(3, 0), 45 + i*38, 475); 
			}
			//columns
			ctx.fillStyle = "green"
			for(var i=0; i<12; i++) { 
			    var dp = data[i]; 
			    ctx.fillRect(40 + i*38, 460-dp * koef , 25, dp * koef); 
			}
			let categoryId = document.getElementById("categoriesGraph").value;
			if(categoryId != 0){
				doGet('http://localhost:8083/items/popular' + '?categoryId=' + categoryId, mostPopularItems);
			};
		}
	}
	xhttp.open("GET", http, true);
	xhttp.send();
}

async function deleteItem(itemId) {
	let response = await fetch("http://localhost:8083/items/" + itemId, {
		method: "DELETE"	 	  	
    });
	await doGet("http://localhost:8083/items" + "?year=" + navigator.getYear() + "&month=" +
	navigator.getMonth(), (obj) => {navigator.setItems(obj)});
	displayItems();
}

function formatDate(date) {
	var d = new Date(date),
		month = '' + (d.getMonth() + 1),
		day = '' + d.getDate(),
		year = d.getFullYear();
	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;
	return [year, month, day].join('-');
}

async function doGet(url, proccess) {
	let response = await fetch(url, {
	method: "GET"});
	let returnedObject = await response.json();
	proccess(returnedObject);
}

async function doPost(url, item) {
	let response = await fetch(url, {
		method: "POST",
		body: item,
		headers: {
 	     'Content-Type': 'application/json'
  	  	}  	  	
    });
    console.log(response.json());
}

async function addItem() {
	//x = document.forms["myForm"]
	let itemName = document.getElementById("item_name").value;
	let itemPrice = document.getElementById("price").value;
	let itemDate = new Date(navigator.getYear(), navigator.getMonth(),
	document.getElementById("days").value);
	let itemCategory;
	await doGet("http://localhost:8083/categories/" +
	document.getElementById("categories").value, function(value) {itemCategory = value});
	await doPost("http://localhost:8083/items", 
	JSON.stringify({ name: itemName, price: itemPrice, category: itemCategory, date: itemDate }));
	document.getElementById('decor').style.display = 'none';
	await doGet("http://localhost:8083/items" + "?year=" + navigator.getYear() + "&month=" +
	navigator.getMonth(), (obj) => {navigator.setItems(obj)});
	displayItems();
}

function mostPopularItems(items){	
	let html = '';
	for(let i = 0; i < items.length; i++){
		let item = items[i];
		html = html + '<tr><td>' + (i +1) + '</td>\n' +
		'        <td>' + item.name + '</td>\n' +
		'        <td>' + item.value + '</td>\n' +
		'        <td>' + item.total.toFixed(2) + '</td>\n' +
		'    </tr>';
	}	
	document.getElementById("mostPopular").getElementsByTagName("tbody")[0].innerHTML = html;
}

function displayItems(){
	let items = navigator.getItems();
	let categories = navigator.getCategories();
	let itemHtml ='';
	items.forEach ((item, index) => {
		console.log(item);
		itemHtml += '<tr><td>' + (index + 1) + '</td>\n' +
		'        <td>' + item.name + '</td>\n' +
		'        <td>' + item.price + '</td>\n' +
		'        <td>' + item.category.name + '</td>' +
		'        <td>' + formatDate(item.date) + '</td>' +
		'        <td><button onclick="deleteItem(' + item.id + ')">Видалити</button></td></tr>';
		});
	document.getElementById("itemsList").getElementsByTagName("tbody")[0].innerHTML = (itemHtml);
	let categoryTable = '';
	categories.forEach(category => {
		categoryTable += '      <tr><td>' + category.name + '</td>' +
		'	 <td>' + items.filter(item => item.category.id == category.id).length +
		'</td></tr>';
		}
	);		
	let resort = true;
    $("#itemsList").trigger("update", [resort]);
	document.getElementById("categoryList").getElementsByTagName("tbody")[0].innerHTML = categoryTable;
	document.getElementById("current_date").innerHTML = monthNames[navigator.getMonth()] +
	 ' ' + navigator.getYear();
	document.getElementById("total_price").innerHTML = "Загальні витрати за місяць: " + 
	items.reduce((value, item) => value + item.price, 0).toFixed(2) + "грн";			
}

function loadCategories() {
	let html = '<option value="">Виберіть категорію:</option>';
	navigator.getCategories().forEach(category => {
		html = html + '<option value="' + category.id + '">' + category.name + '</option>';	
	});
	document.getElementById("categoriesGraph").innerHTML = '<option value="0">Всі витрати</option>' + html.slice(45);
	document.getElementById("categories").innerHTML = html;
}

$(function() {
  $("#itemsList").tablesorter();
});