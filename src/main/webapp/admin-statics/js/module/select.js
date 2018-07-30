/**
 *  下拉框js集合
 *  2018-7-26 
 */
/*
 * 民族下拉框 
 */
$(document).ready(function() {

    var nations = ["汉族","蒙古族","回族","藏族","维吾尔族","苗族","彝族","壮族","布依族","朝鲜族","满族","侗族","瑶族","白族","土家族",
        "哈尼族","哈萨克族","傣族","黎族","傈僳族","佤族","畲族","高山族","拉祜族","水族","东乡族","纳西族","景颇族","柯尔克孜族",
        "土族","达斡尔族","仫佬族","羌族","布朗族","撒拉族","毛南族","仡佬族","锡伯族","阿昌族","普米族","塔吉克族","怒族", "乌孜别克族",
        "俄罗斯族","鄂温克族","德昂族","保安族","裕固族","京族","塔塔尔族","独龙族","鄂伦春族","赫哲族","门巴族","珞巴族","基诺族"];
    var nation = $("#mz");
    for ( var i=0;i<nations.length;i++) {
        var a=nations[i];
        console.log(nations[i]);
        nation.append("<option value='nations[i]'>"+a+"</option>");
    }

})
/*
 * 个人身份下拉框【仅含部分】 
 */
$(document).ready(function() {

    var units = ["在岗职工","研究生毕业年级学生"," 研究生一年级学生"," 研究生其他年级学生",
    	"大学本科毕业年级学生"," 大学本科一年级学生","大学本科其他年级学生","大学专科毕业年级学生",
    	"大学专科一年级学生","大学专科其他年级学生","（转业）待安置、学生毕业未就业的人员",
    	" 公派或因私出国、出境逾期未归的人员","其他"];
    var unit = $("#sf");
    for ( var i=0;i<units.length;i++) {
        var a=units[i];
        console.log(units[i]);
        unit.append("<option value='"+a+"'>"+a+"</option>");
    }

})
/*
 * 学历下拉框【仅含部分】 
 */
$(document).ready(function() {

    var units = ["高中","大专","本科","研究生","硕士","博士"];
    var unit = $("#xl");
    for ( var i=0;i<units.length;i++) {
        var a=units[i];
        console.log(units[i]);
        unit.append("<option value='"+a+"'>"+a+"</option>");
    }

})
