function getAllActiveUsers() {
    $.ajax({
        url: '/user/getAllActiveUsers',
        method: 'GET',
        dataType: 'json',
        success: function (users) {
            $('#idTbody').empty();
            users.forEach(function (user) {
                $('#idTbody').append('<tr>\n' +
                    '<td>'+user.id+'</td>\n' +
                    '<td>'+user.firstname+'</td>\n' +
                    '<td>'+user.lastname+'</td>\n' +
                    '<td>'+user.email+'</td>\n' +
                    '<td>'+user.role.roleType+'</td>\n' +
                    '<td><a style="text-align: center">\n' +
                    '            <i class="fas fa-user-edit" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '<td><a href="#" onclick="blockUser('+user.id+')" style="text-align: center">\n' +
                    '            <i class="fas fa-lock" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '</tr>');
            });
        }
    });
}

function getAllBlockedUsers() {
    $.ajax({
        url: '/user/getAllBlockedUsers',
        method: 'GET',
        dataType: 'json',
        success: function (users) {
            $('#idTbody').empty();
            users.forEach(function (user) {
                $('#idTbody').append('<tr>\n' +
                    '<td>'+user.id+'</td>\n' +
                    '<td>'+user.firstname+'</td>\n' +
                    '<td>'+user.lastname+'</td>\n' +
                    '<td>'+user.email+'</td>\n' +
                    '<td>'+user.role.roleType+'</td>\n' +
                    '<td><a href="#" onclick="activateUser('+user.id+')" style="text-align: center">\n' +
                    '            <i class="fas fa-lock-open" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '</tr>');
            });
        }
    });
}

function blockUser(id) {
    $.ajax({
        url: '/user/blockUserById/'+id,
        method: 'POST',
        success: function () {
            getAllActiveUsers();
        }
    });
}

function activateUser(id) {
    $.ajax({
        url: '/user/activateUserById/'+id,
        method: 'POST',
        success: function () {
            getAllBlockedUsers();
        }
    });
}