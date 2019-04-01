

function getAllActiveTopics() {
    // postStatus = status;

    $.ajax({
        url: '/post/getAllActivePosts',
        method: 'GET',
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
                    '<td>'+post.user.firstname+'</td>\n' +
                    '<td>'+post.user.lastname+'</td>\n' +
                    '<td>'+post.user.email+'</td>\n' +
                    '<td><a onclick="deletePost('+post.id+' ,  '+post.status+')" style="text-align: center">\n' +
                    '            <i class="fas fa-trash-alt" style="display: block"></i>\n' +
                    '        </a></td>\n' +
                    '</tr>');
            });
        }
    });
}

function getAllPendingTopics() {
    topicStatus = status;

    $.ajax({
        url: '/post/getAllPendingPosts',
        method: 'GET',
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
                    '<td>'+post.user.firstname+'</td>\n' +
                    '<td>'+post.user.lastname+'</td>\n' +
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
        method: 'POST',
        success: function () {
            $('#dialog-see-more').dialog('close');
            if (status == 'active') {
                getAllActiveTopics('active');
            } else {
                getAllPendingTopics('pending');
            }
        }
    });
}

function activatePost() {
    $.ajax({
        url: '/post/activatePostById/'+id,
        method: 'POST',
        success: function () {
            $('#dialog-see-more').dialog('close');
            getAllPendingTopics();
        },
        error: function () {
            alert("Internal error!");
        }
    });
}


