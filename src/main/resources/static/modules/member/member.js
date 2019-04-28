$(function() {
    var $table = $("#table-member");

    // updateFilter();
    // update();

    function updateFilter() {
    }


    $('.btn-refresh').click(function(e) {
        update();
    });

    function update() {
        var url = '/members';
        url += '?'+$.param(filter, true);
        console.log(url);
        $table.bootstrapTable({
            url: url
        });

    }
});