$(function(){
	 $(".lbtime").each(function(){
		 let t = $(this).text();
		 $(this).text(getTimeString(t));
	 });
	 
	 $('td input.form-control').datepicker({
		    format: "yyyy-mm-dd",
		    todayBtn: "linked"
	 }).each(dataapi);
	 
});

let dataapi = function(){
	 let classes = this.classList;
	 let url = '/api/';
	 let d = new Date(Date.now() - 1000*60*60*24*3);
	 let m = d.getMonth() + 1;
	 let ds = d.getFullYear() + '-' + (m >=10?m:'0'+m) + '-' +((d.getDate()>=10)?d.getDate():'0'+d.getDate());
	 let target = '';
	 if (classes.contains('gamewot')){
		 url = url + 'wot/' + ds;
		 target = 'gamewot';
	 }else if (classes.contains('gamewowp')){
		 url = url + 'wowp/' + ds;
		 target = 'gamewowp';
	 } else if (classes.contains('gamewows')){
		 url = url + 'wows/' + ds;
		 target = 'gamewows';
	 } else {
		 url = url + 'wotb/' + ds;
		 target = 'gameblitz';
	 }
	 this.value = ds;
	 $.get(url).done(function(stat){
		 $('td.bcount.' +target).text(stat.battlesCount);
		 $('td.lbtime.' +target).text(getTimeString(stat.lastBattleTime));
		console.log(stat);
	 });
	 console.log(url);
 }
function getTimeString(ms){
	let d = new Date(ms*1000);
	let m = d.getMonth() + 1;
	let ds = ((d.getDate()>=10)?d.getDate():'0'+d.getDate()) + '.' + (m >=10?m:'0'+m) + '.' + d.getFullYear();
	return ds;
}