function depProject(){
    $("#depModal").modal('show');
}

function addHistory(){
	$("#addHistory input").val("");
	$("#addHistory select").val("");
    $("#addHistory").modal('show');
}

function editHistory(that){
	//alert($(that).attr("type"))
    $("#addHistory").modal('show');
}

//员工在职状态维护 删除//类型删除（通知和活动)
var tr="";
function maintainModal(nowTr){
    $("#myModal").modal('show');
    tr=$(nowTr).parents("tr");  
 }

function deleteMaintain(){
    var table=tr.parents("table");
    var num=tr.parents("table").find("tbody").find("tr").length;
    tr.remove();
    $("#myModal").modal('hide');    
    for(i=0;i<num;i++){
        table.find("tbody").find("tr").eq(i).find("td").eq(0).text(i);
    }
}

$(function(){
    $(".depchk").click(function(){
        var depName=$(".radios").find("input[type=radio]:checked").parents("tr").find(".name").text();
        $(".Minister").val(depName);
        $("#depModal").modal('hide');
    })

    $(".stationchk").click(function(){
        var stName=$(".radios").find("input[type=radio]:checked").parents("tr").find(".name").text();
     	var stCode=$(".radios").find("input[type=radio]:checked").parents("tr").find(".code").text();
        $(".userName").text(stName+"("+stCode+")");
        $("#depModal").modal('hide');
    })

	$(".addMaintain").click(function(){
	    var module=$(".maintain_list").find("#module").html();
	    $(".maintain_list tbody").append("<tr>"+module+"</tr>");

	    var count=$(".maintain_list tbody").find("tr").length;
	    for(i=0;i<count;i++){
	        $(".maintain_list tbody").find("tr").eq(i).find("td").eq(0).text(i)
	    };
	})

	$(document).delegate(".btn-primary","click",function(){
	    $(this).addClass("displaynone");
	    $(this).parents("td").find(".btn-default").removeClass("displaynone");
	    $(this).parents("tr").find(".state").text("使用中")
	})

	$(document).delegate(".btn-default","click",function(){
	    $(this).addClass("displaynone");
	    $(this).parents("td").find(".btn-primary").removeClass("displaynone");
	    $(this).parents("tr").find(".state").text("暂停使用")
	})

	$(".stationSelect").change(function(){
        var title=$(this).val();
        $(this).parents(".modal_list").find(".title").text(title);
    })

    $(".statchk").click(function(){
    	var count=$(".historyList li").length;

        var cls="rt_text";
        if((count%2) == 0){
            cls="lt_text";
        }else{
            cls="rt_text";
        }

        var state=$(this).parents(".modal-content").find(".stationSelect").find("option:selected").text();
        var title=$(this).parents(".modal-content").find(".stationSelect").val();
        var memo=$(this).parents(".modal-content").find(".memo").val();
        var datef=$(this).parents(".modal-content").find(".datef").val();
        var datet=$(this).parents(".modal-content").find(".datet").val();

        var item="<li class='"+cls+"'><div class='index'><h2>"+state+" <button type='button' class='btn btn-default' onClick='editHistory()'>编辑</button></h2><p class='memo'><span>"+title+"</span>：<span>"+memo+"</span></p><p class='datef'>开始日期：<span>"+datef+"</span></p><p class='datet'>结束日期：<span>"+datet+"</span></p><span class='circle'></span></div>";
        
        $(".historyList").prepend(item);
        $("#addHistory").modal('hide');
    })

    $(".reconect").click(function(){
        $(".Interviewlist").show();
    })

    $(".cancel").click(function(){
        $(".Interviewlist").hide();
    })
})



