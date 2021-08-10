let province=$(".province");
let provinceInfo="<option value='-1'>【请选择省份】</option>";
let city=$(".city");
let cityInfo="<option value='-1'>【请选择城市】</option>";
let town=$(".town");
townInfo="<option value='-1'>【请选择县/区】</option>";
let provinceStr=provinceInfo;
for(let i=0;i<cityData.length;i++){
    if(provinceStr.indexOf(cityData[i].province)==-1){
        if(province.val()==cityData[i].province){
            provinceStr+="<option value='"+cityData[i].province+"' selected>"+cityData[i].province+"</option>";
        }else{
            provinceStr+="<option value='"+cityData[i].province+"'>"+cityData[i].province+"</option>";
        }
    }
}
province.html(provinceStr);
if(province.val()!="-1"){
    let cityStr=cityInfo;
    for(let i=0;i<cityData.length;i++){
        if(province.val()==cityData[i].province&&cityStr.indexOf(cityData[i].city)==-1){
            if(city.val()==cityData[i].city){
                cityStr+="<option value='"+cityData[i].city+"'selected>"+cityData[i].city+"</option>";
            }else{
                cityStr+="<option value='"+cityData[i].city+"'>"+cityData[i].city+"</option>";
            }
        }
    }
    city.html(cityStr);
}
if(city.val()!="-1"){
    let townStr=townInfo;
    for(let i=0;i<cityData.length;i++){
        if(city.val()==cityData[i].city&&townStr.indexOf(cityData[i].district)==-1){
            if(town.val()==cityData[i].district){
                townStr+="<option value='"+cityData[i].district+"' selected>"+cityData[i].district+"</option>"
            }else{
                townStr+="<option value='"+cityData[i].district+"'>"+cityData[i].district+"</option>"
            }
        }
    }
    town.html(townStr);
}
province.change(function () {
    let cityStr=cityInfo;
    let provinceValue=$(this).val();
    city.html(cityInfo);
    town.html(townInfo);
    if($(this).val()!="-1"){
        for(let i=0;i<cityData.length;i++){
            if(provinceValue==cityData[i].province&&cityStr.indexOf(cityData[i].city)==-1){
                if(city.val()==cityData[i].city){
                    cityStr+="<option value='"+cityData[i].city+"' selected>"+cityData[i].city+"</option>";
                }else{
                    cityStr+="<option value='"+cityData[i].city+"'>"+cityData[i].city+"</option>";
                }
            }
        }
        city.html(cityStr);
    }
});
city.change(function () {
    let townStr=townInfo;
    let cityValue=$(this).val();
    town.html(townInfo);
    for(let i=0;i<cityData.length;i++){
        if(cityValue==cityData[i].city&&townStr.indexOf(cityData[i].district)==-1){
            if(town.val()==cityData[i].district){
                townStr+="<option value='"+cityData[i].district+"' selected>"+cityData[i].district+"</option>"
            }else{
                townStr+="<option value='"+cityData[i].district+"'>"+cityData[i].district+"</option>"
            }
        }
    }
    town.html(townStr);
});
