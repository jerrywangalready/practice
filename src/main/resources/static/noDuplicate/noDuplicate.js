$(function () {
    queryList();
});
function getDirectory() {
    $.post("noDuplicate/scan",{},function(data){
        
    });
}

function queryList() {
    // $.post("noDuplicate/queryList",{},function(data){
    //     console.info(1212)
    //     console.info(data)
    //     var html = template('grid_template',{'list':data});
    //     $("#grid").html(html);
    // });

    $.ajax({
        type:'POST',
        url:'noDuplicate/queryList',
        contentType:'application/json',
        data:{},
        success:function (data) {
            var html = template('grid_template',{'list':data});
            $("#grid").html(html);
        }
    });

}

function deleteItem(id, path) {
    $.post("noDuplicate/deleteItem",{id : id, path:path},function(data){
        queryList();
    });
}