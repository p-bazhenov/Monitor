$(function(){
	 $(".lbtime").each(function(){
		 let t = $(this).text();
		 let d = new Date(t*1000);
		 let m = d.getMonth() + 1;
		 let ds = ((d.getDate()>=10)?d.getDate():'0'+d.getDate()) + '.' + (m >=10?m:'0'+m) + '.' + d.getFullYear();
		 $(this).text(ds);
	 });
	 
	 $('td input').datepicker({
		    format: "dd.mm.yyyy",
		    todayBtn: "linked"
	 });
	 
	 
});