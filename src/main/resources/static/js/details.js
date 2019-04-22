var updateUrl;
$(document).ready(function() {
    updateUrl = window.location.href+"post/getAllActivePosts";
    dataTabelConfig(updateUrl);
    $('#IdActivePosts').click(function () {

        $('#example').dataTable().fnDestroy();
        updateUrl = "/post/getAllActivePosts";
        dataTabelConfig(updateUrl);
        $("#IdPage").html("<i class=\"fa fa-angle-right\"></i> Active posts");
    });

    $("#IdPendingPosts").click(function () {
        $('#example').dataTable().fnDestroy();
        updateUrl = "/post/getAllPendingPosts";
        dataTabelConfig(updateUrl);
        $("#IdPage").html("<i class=\"fa fa-angle-right\"></i> Pending posts");
    });

    $("#IdActiveUsers").click(function () {
        $('#example').dataTable().fnDestroy();
        userDataTable("/user/getAllActiveUsers");
        $("#IdPage").html("<i class=\"fa fa-angle-right\"></i> Active users");
    });

    $("#IdBlockedUser").click(function () {
        $('#example').dataTable().fnDestroy();
        userDataTable("/user/getAllBlockedUsers");
        $("#IdPage").html("<i class=\"fa fa-angle-right\"></i> Blocked users");
    });

    $("#IdInactiveUsers").click(function () {
        $('#example').dataTable().fnDestroy();
        userDataTable("/user/getAllInactiveUsers");
        $("#IdPage").html("<i class=\"fa fa-angle-right\"></i> Pending users");
    });

    $("#dialog-confirm").dialog({
        resizable: false,
        height: window.innerHeight-50,
        width: '70%',
        modal: true,
        autoOpen: false,
        open: function(){
            jQuery('.ui-widget-overlay').bind('click',function(){
                jQuery('#dialog-confirm').dialog('close');
            });
        },
        buttons: {
            "Delete": function () {
                if (confirm('Are you sure delete this post?')) {
                    $.ajax({
                        url : '/post/delete-post/'+fieldId,
                        success : function (e) {
                            alert('Post successfully deleted');
                        }
                    });
                    $(this).dialog("close");
                    location.reload();
                }
            },

            "Update status": function () {
                if (confirm("Are you sure update status this post")){
                    $.ajax({
                        url : '/post/update-post/'+fieldId,
                        type: "get",
                        data: $("#id-form-post").serialize(),
                        success : function (e) {
                            alert("Post successfully updated");
                        }
                    });
                    $("#dialog-confirm").dialog("close");
                    $('#example').dataTable().fnDestroy();
                    dataTabelConfig(updateUrl);
                }
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    });
} );



function dataTabelConfig(url) {
    $("#example").DataTable().destroy();
    $("#example").empty();
    $('#example').DataTable({
        reponsive: true,
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "columns": [
            {"data": "idPost", title: "Id Post"},
            {"data": "title", title: "Title"},
            {"data": "postType", title: "Post Type"},
            {"data": "price", title: "Price"},
            {"data": "area", title: "Area"},
            {"data": "city.cityName", title: "City name"},
            {"data": "user.firstName", title: "First name"},
            {"data": "user.lastName", title: "Last name"},
            {"data": "user.email", title: "User email"},
            {"data": "idPost", title: "View details",
                "render": function (data, type, full, meta) {
                    return '<button id="'+full.idPost+'" onclick="get_post_detail(this.id)" class="btn btn-cyan" style=\'text-align: center\'>' +
                        'View details</button>';
                }
            }
        ]
    });
}

function userDataTable(url) {
    updateUrl = url;
    $("#example").DataTable().destroy();
    $("#example").empty();
    $('#example').DataTable({
        reponsive: true,
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "columns": [
            {"data": "idUser", title: "Id user"},
            {"data": "email", title: "E-mail"},
            {"data": "firstName", title: "First name"},
            {"data": "lastName", title: "Last name"},
            {"data": "status", title: "Status"},
            {"data": "role.roleType", title: "Role type"},
            {title: "Edit detail", "render": function (data, type, full, meta) {
                return '<button id="'+full.idUser+'" onclick="get_user_detail(this.id)" class="btn btn-cyan" style=\'text-align: center\'>' +
                    'Edit details</button>';
            }}
        ]
    });
}

var fieldId;
function get_post_detail(id) {
    fieldId = id;
    $.ajax({
        url : '/post/post-detail/'+fieldId,
        type : "get",
        dataType : 'html',
        success : function (e) {
            $("#dialog-confirm").html(e).dialog("open");
        }
    });
}

function userDialog() {
    $("#dialog-confirm").dialog("destroy");
    $("#dialog-confirm").dialog({
        resizable: false,
        height: window.innerHeight-50,
        width: '70%',
        modal: true,
        autoOpen: false,
        open: function(){
            jQuery('.ui-widget-overlay').bind('click',function(){
                jQuery('#dialog-confirm').dialog('close');
            });
        },
        buttons: {
            "Delete": function () {
                if (confirm('Are you sure delete this post?')) {
                    $.ajax({
                        url : '/user/delete-user/'+fieldId,
                        success : function (e) {
                            alert('User successfully deleted');
                        }
                    });
                    $("#dialog-confirm").dialog("close");
                    $('#example').dataTable().fnDestroy();
                    userDataTable(updateUrl);
                }
            },

            "Update status": function () {
                if (confirm("Are you sure update status this user")){
                    $.ajax({
                        url : '/user/update-user/'+fieldId,
                        type: "get",
                        data: $("#id-form-user").serialize(),
                        success : function (e) {
                            alert("User successfully updated");
                        }
                    });
                    $("#dialog-confirm").dialog("close");
                    $('#example').dataTable().fnDestroy();
                    userDataTable(updateUrl);
                }
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    });
}

function get_user_detail(id) {
    fieldId = id;
    userDialog();
    $.ajax({
        url : '/user/user-detail/'+fieldId,
        type : "get",
        dataType : 'html',
        success : function (e) {
            $("#dialog-confirm").html(e).dialog("open");
        }
    });
}