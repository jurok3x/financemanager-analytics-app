const monthNames = ["Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
	"Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"];
var d = new Date();
var categories;
var items;
const comparator = ["", "name", "price", "category", "date"];
var month = d.getMonth();
var year = d.getFullYear();
var httpRequest = "http://localhost:8083/item/findAll" + "?year=" + year + "&month=" + month; 
loadItems(httpRequest);
daysList(year, month);
drawGraph(0);
getMinYear();



function drawGraph() {
	let id = document.getElementById("categoriesGraph").value;
	let year = document.getElementById("yearGraph").value; 
	if(this.year == ''){this.year = d.getFullYear()};
	var xhttp = new XMLHttpRequest();
	var http = (id != 0)? 'http://localhost:8083/item/findByCategoryId/' + id + '?year=' + year :
	'http://localhost:8083/item/findAll' + "?year=" + year;
	xhttp.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			let items = JSON.parse(this.responseText);		
			var data = new Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
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
			mostPopularItems();
		}
	}
	xhttp.open("GET", http, true);
	xhttp.send();
}

function getMinYear(){
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200){
		let items = JSON.parse(this.responseText);
		var minYear = items[0].date.slice(0, 4) / 1;
		var html;
		for(var i=0; i<=(d.getFullYear() - minYear); i++){
			html += '<option value="' + (d.getFullYear() - i) + '">' + (d.getFullYear() - i) + '</option>';
		}
		document.getElementById("yearGraph").innerHTML = html;
	}
	};
	xhttp.open("GET", 'http://localhost:8083/item/findAll' + "?sortBy=date", true);
	xhttp.send();
}

function mostPopularItems(){
	let categoryId = document.getElementById("categoriesGraph").value;
	let http = 'http://localhost:8083/item/getMostFrequentItems';
	if(categoryId != 0){http += '?categoryId=' + categoryId};
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200){
		let objects = JSON.parse(this.responseText);
		var html = '<caption>Найбільш популярні</caption><tr>\n' +
		'        <th><a>№</a></th>\n' +
		'        <th><a>Назва</a></th>\n' +
		'        <th><a>Кількість</a></th>\n' +
		'        <th><a>Загальна ціна</a></th>\n' +
		'    </tr>';
		
		for(var i=0; i<objects.length; i++){
			var object = objects[i];
			html = html + '<tr><td>' + (i +1) + '</td>\n' +
			'        <td>' + object.name + '</td>\n' +
			'        <td>' + object.value+ '</td>\n' +
			'        <td>' + object.total + '</td>\n' +
			'    </tr>';
		}
		
		document.getElementById("mostPopular").innerHTML = html;
		}
	};
	xhttp.open("GET", http, true);
	xhttp.send();
}

function loadItems(httpRequest) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			items = JSON.parse(this.responseText);
			displayItems(items);	
		}
	};
	xhttp.open("GET", httpRequest, true);
	xhttp.send();
	loadCategories();
}

function displayItems(items){
var html = '<caption id="table_head">' +
		'		 <h1 id="total_price"></h1>' +
		'		 <button id="add" onclick="document.getElementById(\'decor\').'+
		'        style.display = \'block\'">&#10010;</a>' +
		'		 </caption>' +
		'		 <tr>\n' +
		'        <th><a onclick="sortItems(0)">№</a></th>\n' +
		'        <th><a onclick="sortItems(1)">Назва</a></th>\n' +
		'        <th><a onclick="sortItems(2)">Ціна</a></th>\n' +
		'        <th><a onclick="sortItems(3)">Категорія</a></th>\n' +
		'        <th><a onclick="sortItems(4)">Дата</a></th>\n' +
		'        <th>Редагувати</th>\n' +
		'    </tr>';
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		console.log(item);
		html = html + '<tr><td>' + (i + 1) + '</td>\n' +
			'        <td>' + item.name + '</td>\n' +
			'        <td>' + item.price + '</td>\n' +
			'        <td>' + item.category.name + '</td>' +
			'        <td>' + formatDate(item.date) + '</td>' +
			'        <td><button onclick="deleteItem(' + item.id + ')">Видалити</button></td></tr>';
			}
			document.getElementById("itemsList").innerHTML = html;
			document.getElementById("current_date").innerHTML = monthNames[month] + ' ' + year;
			document.getElementById("total_price").innerHTML = "Загальні витрати за місяць: " + getTotalPrice(items).toFixed(2) + "грн";
}

function sortItems(type) {
	if(type == 0){items.sort(function(a, b){return a.id - b.id})};
	if(type == 1){items.sort(function(a, b){
		var x = a.name.toLowerCase();
 		var y = b.name.toLowerCase();
 		if (x < y) {return -1;}
  		if (x > y) {return 1;}
  		return 0;
	})};
	if(type == 2){items.sort(function(a, b){return a.price - b.price})};
	if(type == 3){items.sort(function(a, b){return a.category.id - b.category.id})};
	if(type == 4){items.sort(function(a, b){return a.date - b.date})};
	displayItems(items);
}

function getTotalPrice(items) {
	var sum = 0;
	for (let i = 0; i < items.length; i++) {
		item = items[i];
		sum += item.price;
	}
	return sum;
}

function deleteItem(itemId) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			loadItems(httpRequest);
		}
	}
	xhttp.open("DELETE", "http://localhost:8083/item/delete/" + itemId, true);
	xhttp.send();	
}


function monthBack() {
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
	httpRequest = "http://localhost:8083/item/findAll" + "?year=" + year + "&month=" + month;
	loadItems(httpRequest);
	daysList(year, month);
}

function monthForward() {
	if (month != d.getMonth() || year != d.getFullYear()) {
		month++;
		if(month == d.getMonth() && year == d.getFullYear()){
			document.getElementById("forward").className = "disabled";
		}
			if (month > 11) {
				year++;
				month = 0;
		} 		
		httpRequest = "http://localhost:8083/item/findAll" + "?year=" + year + "&month=" + month;
		loadItems(httpRequest);
		daysList(year, month);	
	}
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

function loadCategories() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			categories = JSON.parse(this.responseText);
			var html = '<option value="">Виберіть категорію:</option>';
			var table = '<caption><h1>По категоріям</h1></caption><tr>' +
						'		<th>Категорія</th>' +
						'	<th>Кількість</th></tr>';
			var categoryCount;
			var link;
			for (var i = 0; i < categories.length; i++) {
				var category = categories[i];
				console.log(category);
				link = 'http://localhost:8083/item/itemsCount/' + (i + 1) + '?month=' + month + '&year=' + year;
				categoryCount =  getCategoryCount(link);	
				html = html + '<option value="' + category.id + '">' + category.name + '</option>';
				table += '      <tr><td>' + category.name + '</td>' +
					     '		  <td>' + categoryCount + '</td></tr>';
			}
			document.getElementById("categories").innerHTML = html;
			document.getElementById("categoriesGraph").innerHTML = '<option value="0">Всі витрати</option>' + html.slice(45);
			document.getElementById("categoryList").innerHTML = table;
		}
	};
	xhttp.open("GET", "http://localhost:8083/category/findAll", true);
	xhttp.send();
}

	function getCategoryCount(link){
		var request = new XMLHttpRequest();
		request.open("GET", link, false);
		request.send();
		return request.responseText;
}

function addItem() {
	var xmlhttp = new XMLHttpRequest();// new HttpRequest instance
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			document.getElementById('decor').style.display = 'none';
			loadItems(httpRequest);
		}
	}
	var itemName = document.getElementById("item_name").value;
	var itemPrice = document.getElementById("price").value;
	var itemDate = new Date(year, month, document.getElementById("days").value)
	var itemCategory = getCategoryById(document.getElementById("categories").value);
	xmlhttp.open("POST", "http://localhost:8083/item/save", true);
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(JSON.stringify({ name: itemName, price: itemPrice, category: itemCategory, date: itemDate }));
}

function getCategoryById(id) {
	var xhttp = new XMLHttpRequest();
	xhttp.open("GET", "http://localhost:8083/category/" + id, false);
	xhttp.send();
	return JSON.parse(xhttp.responseText);
}

function daysList(year, month) {
	var y = year;
	var m = month + 1;
	var daysInMonth = /8|3|5|10/.test(--m)?30:m==1?(!(y%4)&&y%100)||!(y%400)?29:28:31;
	var html = '<option value="">День:</option>';
	for (var i = 1; i <= daysInMonth; i++) {
		html = html + '<option value="' + i + '">' + i + '</option>';
	}
	document.getElementById("days").innerHTML = html;
}
