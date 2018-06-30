$(function(){
	
	$("span.date").each(function(){
		let d = $(this).text().substring(0, 19);
		$(this).text(d);
	});
	
	$("div.json").each(function(){
		let data = JSON.parse($(this).text());
		let keys = Object.keys(data.statistics);
		for (let k in keys){
			let val = data.statistics[keys[k]];
			let row = $("tbody tr."+k);
			if (row.length == 0){
				row = $("<tr>").append($("<td>").text(keys[k])).addClass(k);
				$("table tbody").append(row);
			}
			if (val != null){
				if (typeof val == "object" ){
					let t = createInnerTable(val);
					row.append($("<td>").append(t));
				}else{
					row.append($("<td>").text(val));
				}
			}
		}
	});
	
});

function createInnerTable(dataObject){
	let keys = Object.keys(dataObject);
	let innerTable = $("<table>");
	
	for (let k in keys){
		let row = $("<tr>")
			.append($("<td>").text(keys[k]))
			.append($("<td>").text(dataObject[keys[k]]));
		innerTable.append(row);
	}
	
	return innerTable;
}