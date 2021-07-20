function deleteEmp(id) {
    let flag=confirm("您确认要删除"+id+"号员工吗?");
    if (flag){
        location.href="http://localhost:8080/Day04_PM/delete?id="+id
    }
}
function editItem(obj) {
    let tds=$(obj).parent().parent().children();

    let id=tds.eq(0).text();
    let tno=tds.eq(1).text();
    let name=tds.eq(2).text();
    let gender=tds.eq(3).text();
    let birthday=tds.eq(4).text();
    let title=tds.eq(5).text();
    let salary=tds.eq(6).text();
    let managerId=tds.eq(7).text();
    let deptId=tds.eq(8).text();
    $('#id').val(id);
    $('#tno').val(tno);
    $('#name').val(name);
    $('#gender').val(gender);
    $('#birthday').val(birthday);
    $('#title').val(title);
    $('#salary').val(salary);
    $('#managerId').val(managerId==="boss"?"":managerId);
    $('#deptId').val(deptId==="轮岗"?"":deptId);

    $('#editModal').modal('show');
}

function searchByKw() {
    // console.log($('#keyword').val());
    let kwPattern=/^[a-zA-Z\u4e00-\u9fa5]+$/;
    if (kwPattern.test($('#keyword').val())){
        $('#keyword').parent().parent().submit();
    }else {
        alert("请正确输入关键词")
    }
}