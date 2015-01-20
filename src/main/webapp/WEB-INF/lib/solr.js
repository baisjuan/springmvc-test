$('#delete_documents').click(function () {
    $.ajax({
        type: 'DELETE',
        url: 'solr/deleteDocuments',
        success: print
    });
})

$('#commit').click(function () {
    $.ajax({
        type: 'GET',
        url: 'solr/commit',
        success: print
    });
})

$('#add_document').click(function () {
    $.ajax({
        type: 'PUT',
        url: 'solr/addDocument',
        success: print
    });
})

$('#add_documents').click(function () {
    $.ajax({
        type: 'PUT',
        url: 'solr/addDocuments',
        success: print
    });
})

$('#exe_search').click(function () {
    $.ajax({
        type: 'GET',
        url: 'solr/exeSearch',
        success: print
    });
})