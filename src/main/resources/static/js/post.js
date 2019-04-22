

function getAllActiveTopics() {

    $.ajax({
        url: '/post/getAllActivePosts',
        type: 'GET',
        dataType: 'json',
        success: function (posts) {
            $('#idTbody').empty();
            posts.forEach(function (post) {
                $('#idTbody').append('<tr>\n' +
                    '<td>'+post.idPost+'</td>\n' +
                    '<td>'+post.title+'</td>\n' +
                    '<td>'+post.shareDate+'</td>\n' +
                    '<td>'+post.address+'</td>\n' +
                    '<td>'+post.postType+'</td>\n' +
                    '<td>'+post.homeType+'</td>\n' +
                    '<td>'+post.price+'</td>\n' +
                    '<td>'+post.area+'</td>\n' +
                    '<td>'+post.roomCount+'</td>\n' +
                    '<td>'+post.status+'</td>\n' +
                    '<td>'+post.city.cityName+'</td>\n' +
                    '<td>'+post.user.firstName+'</td>\n' +
                    '<td>'+post.user.lastName+'</td>\n' +
                    '<td>'+post.user.email+'</td>\n' +
                    '<td><a onclick="deletePost('+post.idPost+' ,  '+post.status+')" style="text-align: center">\n' +
                    '            <i class="fas fa-trash-alt" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '</tr>');
            });
        }
    });
}

function getAllPendingTopics() {

    $.ajax({
        url: '/post/getAllPendingPosts',
        type: 'GET',
        dataType: 'json',
        success: function (topics) {
            $('#idTbody').empty();
            topics.forEach(function (topic) {
                $('#idTbody').append('<tr>\n' +
                    '<td>'+post.idPost+'</td>\n' +
                    '<td>'+post.title+'</td>\n' +
                    '<td>'+post.shareDate+'</td>\n' +
                    '<td>'+post.address+'</td>\n' +
                    '<td>'+post.postType+'</td>\n' +
                    '<td>'+post.homeType+'</td>\n' +
                    '<td>'+post.price+'</td>\n' +
                    '<td>'+post.area+'</td>\n' +
                    '<td>'+post.roomCount+'</td>\n' +
                    '<td>'+post.status+'</td>\n' +
                    '<td>'+post.city.cityName+'</td>\n' +
                    '<td>'+post.user.firstName+'</td>\n' +
                    '<td>'+post.user.lastName+'</td>\n' +
                    '<td>'+post.user.email+'</td>\n' +
                    '<td><a onclick="deletePost('+post.id+' ,  '+post.status+')" style="text-align: center">\n' +
                    '            <i class="fas fa-trash-alt" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                  '<td><a onclick="activatePost('+post.id+')" style="text-align: center">\n' +
                    '            <i class="fas fa-trash-alt" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '</tr>');
            });
        }
    });
}

function deletePost(id, status) {

    $.ajax({
        url: '/post/deletePost/'+id,
        type: 'post',
        success: function () {
            $('#dialog-see-more').dialog('close');
            if (status == 'active') {
                getAllActiveTopics();
            } else {
                getAllPendingTopics();
            }
        }
    });
}

function activatePost(id) {
    $.ajax({
        url: '/post/activatePostById/'+id,
        type: 'post',
        success: function () {
            $('#dialog-see-more').dialog('close');
            getAllPendingTopics();
        },
        error: function () {
            alert("Internal error!");
        }
    });
}


