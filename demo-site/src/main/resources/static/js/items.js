function loadItems(httpRequest) {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var items = JSON.parse(this.responseText);
			var html = '<caption id="table_head">' +
		'		 <h1 id="total_price"></h1>' +
		'		 <button id="add" onclick="document.getElementById(\'decor\').'+
		'        style.display = \'block\'">&#10010;</a>' +
		'		 </caption>' +
		'		 <tr>\n' +
		'        <th><a onclick="sort(0)">№</a></th>\n' +
		'        <th><a onclick="sort(1)">Назва</a></th>\n' +
		'        <th><a onclick="sort(2)">Ціна</a></th>\n' +
		'        <th><a onclick="sort(3)">Категорія</a></th>\n' +
		'        <th><a onclick="sort(4)">Дата</a></th>\n' +
		'        <th>Редагувати</th>\n' +
		'    </tr>';
	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var classname;
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
	};
	xhttp.open("GET", httpRequest, true);
	xhttp.send();
	loadCategories();
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
	httpRequest = "http://localhost:8083/item/findDate/" + year + "/" + month;
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
		httpRequest = "http://localhost:8083/item/findDate/" + year + "/" + month;
		loadItems(httpRequest);
		daysList(year, month);	
	}
	

}

function sort() {
	if(httpRequest.search("=") == -1){
		httpRequest = "http://localhost:8083/item/findDate/" + year + "/" + month + "?sortBy=" +
		comparator[arguments[0]] + "&reversed=" + false;
	} else{
		httpRequest = "http://localhost:8083/item/findDate/" + year + "/" + month + "?sortBy=" +
		comparator[arguments[0]] + "&reversed=" + /false/.test(httpRequest);
	}
	loadItems(httpRequest);
}

function getTotalPrice(items) {
	var sum = 0;
	for (var i = 0; i < items.length; i++) {
		item = items[i];
		sum += item.price;
	}
	return sum;
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
				html = html + '<option value="'+ category.id + '">' + category.name + '</option>';
				table += '      <tr><td>' + category.name + '</td>' +
					     '		  <td>' + categoryCount + '</td></tr>';
			}
			document.getElementById("categories").innerHTML = html;
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


const monthNames = ["Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень",
	"Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"];
var d = new Date();
var categories;

const comparator = ["", "name", "price", "category", "date"];
var month = d.getMonth();
var year = d.getFullYear();
var httpRequest = "http://localhost:8083/item/findDate/" + year + "/" + month; 
loadItems(httpRequest);

daysList(year, month);

