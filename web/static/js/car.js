function showDescription(id){
div = gid("description_form");
div.style.visibility="visible";
div2 = gid("id");
div2.value = id;
var url = 'http://imbi.ld.ttu.ee:7500/t093817_Shirt/shirtService?id='+id;


$.getJSON(url, function (data) {
	$("#desc").val(data.description);
   
    
});

}

function hide_description_form(){
div = gid("description_form");
div.style.visibility="hidden";
}
function gid(id){
return document.getElementById(id);
}