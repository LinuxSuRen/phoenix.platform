<div class="container">
    <h1>${title}</h1>
    <p class="toolbar">
        <#list buttons?keys as name>
        <a class="${name} btn btn-default" href="javascript:">${buttons['${name}']}</a>
        </#list>
        <span class="alert"></span>
    </p>
    <table id="table"
           data-show-refresh="true"
           data-show-columns="true"
           data-search="true"
           data-query-params="queryParams"
           data-toolbar=".toolbar">
        <thead>
        <tr>
			<#list columns?keys as name>
			<th data-field="${name}">${columns['${name}']}</th>
			</#list>
            <th data-field="action"
                data-align="center"
                data-formatter="actionFormatter"
                data-events="actionEvents">Action</th>
        </tr>
        </thead>
    </table>
</div>

<script type="text/javascript">
    var API_URL = '${api}';
    var $table = $('#table').bootstrapTable({url: API_URL + '${listUri}'}),
            $modal = $('#modal').modal({show: false}),
            $alert = $('.alert').hide();

    $(function () {
        $modal.find('.submit').click(function () {
            var row = {};
            $modal.find('input[name]').each(function () {
                row[$(this).attr('name')] = $(this).val();
            });
            $modal.find('select[name]').each(function () {
                row[$(this).attr('name')] = $(this).val();
            });
            $.ajax({
                url: API_URL,
                type: $modal.data('id') ? 'put' : 'post',
                contentType: 'application/json',
                data: JSON.stringify(row),
                success: function () {
                    $modal.modal('hide');
                    $table.bootstrapTable('refresh');
                    showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item successful!', 'success');
                },
                error: function () {
                    $modal.modal('hide');
                    showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item error!', 'danger');
                }
            });
        });
    });

    function queryParams(params) {
        return {};
    }
    function actionFormatter(value) {
        return [
            '<a class="locator" href="javascript:" title="Edit Locators"><i class="glyphicon glyphicon-edit"></i></a>',
            '<a class="update" href="javascript:" title="Update Item"><i class="glyphicon glyphicon-edit"></i></a>',
            '<a class="remove" href="javascript:" title="Delete Item"><i class="glyphicon glyphicon-remove-circle"></i></a>',
        ].join('');
    }
    // update and delete events
    window.actionEvents = {
        'click .locator': function (e, value, row) {
            window.location = '/field_locator/list?fieldId=' + row.id;
        },
        'click .update': function (e, value, row) {
            showModal($(this).attr('title'), row);
        },
        'click .remove': function (e, value, row) {
            if (confirm('Are you sure to delete this item?')) {
                $.ajax({
                    url: API_URL + row.id,
                    type: 'delete',
                    success: function () {
                        $table.bootstrapTable('refresh');
                        showAlert('Delete item successful!', 'success');
                    },
                    error: function () {
                        showAlert('Delete item error!', 'danger');
                    }
                })
            }
        }
    };
    function showModal(title, row) {
        row = row || {
            id: '',
            name: '',
            stargazers_count: 0,
            forks_count: 0,
            description: ''
        }; // default row value
        $modal.data('id', row.id);
        $modal.find('.modal-title').text(title);
        for (var name in row) {
            $modal.find('input[name="' + name + '"]').val(row[name]);
        }
        $modal.modal('show');
    }
    function showAlert(title, type) {
        $alert.attr('class', 'alert alert-' + type || 'success')
                .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
        setTimeout(function () {
            $alert.hide();
        }, 3000);
    }
    function sysHelp(){
        introJs().setOption('done', 'next').start().oncomplete(function(){
        });
    }

    $(document).ready(function() {
        $('form').bootstrapValidator();
    });
</script>