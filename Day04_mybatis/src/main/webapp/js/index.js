// 通过id来删除员工
function deleteEmp(id) {
    let flag = confirm("您确定要删除"+id+"号员工吗？");
    if(flag){
        location.href = "http://localhost:8080/Day04_PM/delete?id="+id;
    }
}

// 编辑模态框
function editItem(obj) { // obj默认是JS对象
    let tds = $(obj).parent().parent().children();
    //1. 通过jquery来获取选择的数据
    let eid = tds.eq(0).text();
    let tno = tds.eq(1).text();
    let name = tds.eq(2).text();
    let gender = tds.eq(4).text();
    let birthday = tds.eq(5).text();
    let title = tds.eq(6).text();
    let salary = tds.eq(7).text();
    let managerId = tds.eq(8).text();
    let deptId = tds.eq(9).text();
    //2. 将选择的数据同步到模态框中
    $('#eid').val(eid);
    $('#tno').val(tno);
    $('#ename').val(name);
    $('#birthday').val(birthday);
    $('#gender').val(gender);
    $('#title').val(title);
    $('#salary').val(salary);
    $('#managerId').val(managerId);
    $('#deptId').val(deptId);
    //3. 让模态框显示出来
    $('#editModal').modal('show');  // 让模态框动态显示出来
}

/**
 * 更新头像上传的模态框数据
 */
function uploadImg(obj){
    $('#showImg').hide(); // 让图片预览隐藏
    let tds = $(obj).parent().parent().children();
    //1. 通过jquery来获取选择的数据
    let eid = tds.eq(0).text();
    let tno = tds.eq(1).text();
    let name = tds.eq(2).text();
    //2. 更新模态框数据
    $('#empid').val(eid);
    $('#emptno').val(tno);
    $('#empname').val(name);
    //3. 动态显示出模态框
    $('#uploadModal').modal('show');
}

$(function () {
    let imgFlag = false;
    // 1. 头像预览
    $('#myImg').change(function () {
        let file = this.files[0];
        let imgPattern = /image\/\w+/;// 用来匹配以 image/
        if(!imgPattern.test(file.type)){
            alert("文件必须为图片！");
            imgFlag = false;
            return false;
        }
        let reader = new FileReader(); // 创建文件预览器
        reader.readAsDataURL(file);
        reader.onload = function () {
            $('#showImg').html("<img src="+this.result+" width='150px';height='150px' class='img-circle'>");
            $('#showImg').slideDown(1000);
            imgFlag = true;
        }
    });

    //2. 手动提交文件上传的表单
    $('#btn_upload').click(function () {
        if($('#empid').val()!= '' && imgFlag){
            $('#uploadForm').submit(); // 让文件上传的表单提交
        }else{
            alert("请选择正确的头像图片");
        }
    })
})
