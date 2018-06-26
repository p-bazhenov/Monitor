$(function(){
	
	$("span.date").each(function(){
		let d = $(this).text().substring(0, 19);
		$(this).text(d);
	});
	
	$("div.json").each(function(){
		let data = JSON.parse($(this).text());
		let keys = Object.keys(data);
		for (let k in keys){
			let val = data[keys[k]];
			let row = $("tbody tr."+k);
			if (row.length > 0){
				continue;
			}
			let r = $("<tr>").append($("<td>").text(keys[k]));
			$("table tbody").append(r);
		}
	});
	
});