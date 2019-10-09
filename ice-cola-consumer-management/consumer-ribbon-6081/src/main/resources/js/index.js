function getAll() {
    $.ajax({
        url:"/role/selectAll",
        type:"get",
        success:function (data) {
            data = data.result[0];
            append(data);
        },
        error:function () {
            alert("error");
        }
    })
}

function append(data){
    $("#roles").empty();
    for(var i=0;i<data.length;i++){
        var tr = "<tr>";
        tr += "<td>"+data[i].id+"</td>";
        tr += "<td>"+data[i].name+"</td>";
        tr += "<td>"+data[i].title+"</td>";
        tr += "<td>"+data[i].world+"</td>";
        tr += "<td>"+data[i].img+"</td>";
        tr += "<td><input type='button' value='删除' class='btn btn-primary' onclick='del("+data[i].id+")'/>" +
            "<input type='button'value='修改' onclick='update("+data[i].id+")' class='btn btn-default' data-toggle='modal' data-target='#myModal'/>" +
            "</td></tr>";
        $("#roles").append(tr);
    }
}

function del(id){
    var flag = confirm("确定删除？");
    if(flag){
        $.ajax({
            type:"get",
            url:"/role/delete",
            data:{"id":id},
            success:function (data) {
                location="/";
            },
            error:function () {
                alert("error");
            }
        })
    }
}

function addDataFromMysql(){
    $.ajax({
        type:"get",
        url:"/role/caCheClear",
        success:function (data) {
            location="/";
        },
        error:function () {
            alert("error");
        }
    })
}

function update(id){
    $.ajax({
        type:"get",
        url:"/role/selectById",
        data:{"id":id},
        success:function (data) {
            data = data.result[0];
            $("#id").val(data.id);
            $("#xm").val(data.name);
            $("#title").val(data.title);
            $("#world").val(data.world);
            $("#img").val(data.img);
        },
        error:function () {
            alert("error");
        }
    })
}

function insert(){
    $("#id").val("");
    $("#xm").val("");
    $("#title").val("");
    $("#world").val(-1);
    $("#img").val("");
}

function insertOrUpdateRole(){
    var role = $("#roleForm").serialize();
    $.ajax({
        type:"post",
        url:"/role/insertOrUpdateRole",
        data:role,
        success:function () {
            location="/";
        },
        error:function () {
            alert("error");
        }
    })
}

$(function () {
    getAll();
})