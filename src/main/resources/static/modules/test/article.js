$(function() {


    updateFilter();
    update();

    // var $table = $("#table-article");
    // console.log($table.data("url"));


    function updateFilter() {

        $( 'select[name=org]' ).selectpicker('val', filter.org);
        $( 'select[name=category]' ).selectpicker('val', filter.category);

        if (filter.riskLevel !== null ) {
            var riskLevel = filter.riskLevel.reduce(function(o, val) {
                o[val] = true;
                return o;
            }, {});
            $("input[name=riskLevel]").each(function(i) {
                if (riskLevel[ $(this).val() ]) {
                    $(this).prop('checked', riskLevel[ $(this).val() ]);
                }
            });

        }

        // $()
// console.log(filter.riskLevel.join('"],[value="') );
        // $('input[name=riskLevel] [value="'+filter.riskLevel.join('"],[value="')+'"]').prop('checked',true);



    }


    $('.btn-refresh').click(function(e) {
        // console.log('click');

        update();
    });

    // function queryParams(params) {
    //     params = filter;
    //     return params;
    // }

    function update() {
        console.log(filter);
        var url = '/test/articles';
        url += '?'+$.param(filter, true);

        //console.log(url);

        // var param = {
        //     startDate: filter.startDate,
        //     endDate: filter.endDate,
        //     title: filter.title,
        // };
        // url += '?'+$.param(param)+'&category=1&category=2';

        // var param2 = {
        //     category: filter.category,
        // };
        // url += '?'+$.param(param);

        console.log(url);
        //
        // var paramArr = {
        //     org: filter.org,
        //     category: filter.category,
        //     riskLevel: filter.riskLevel,
        // };
        // // url += '?'+$.param(param)+'&'+decodeURIComponent($.param(paramArr));
        // url += '?'+$.param(filter);
        // console.log(url);
        // $("#table-article")

        // var $table = $("#table-article");
        // console.log( $table.data('url') );
        $("#table-article").bootstrapTable({
            url: url
        });

    }
});