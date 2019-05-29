/**
 * 
 */
function getHost(){
	return "http://39.100.99.143:8082";
}
function getQueryString(name) { 
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return (r[2]); 
	return null; 
}
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
    }
    return(false);
}