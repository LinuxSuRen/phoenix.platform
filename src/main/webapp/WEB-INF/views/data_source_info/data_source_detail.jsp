<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/META-INF/suren.tld" prefix="su" %>
<%String basePath=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title>数据源明细</title>
    <su:link href="/static/jquery-easyui/themes/default/easyui.css"></su:link>
    <su:link href="/static/jquery-easyui/themes/icon.css"></su:link>
    <su:script src="/static/jquery/jquery.min.js"></su:script>
    <su:script src="/static/jquery-easyui/jquery.easyui.min.js"></su:script>
</head>
<body>

<script type="text/javascript">
    $(function(){
        $('#dg').datagrid({
            title:'Editable DataGrid',
            iconCls:'icon-edit',
            width:660,
            height:250,
            singleSelect:true,
            method:'get',
            url:'<%=basePath%>/api/projects',
            columns:[[
                {field:'name',title:'Attribute',width:180,editor:'text'},
                {field:'action',title:'Action',width:80,align:'center',
                    formatter:function(value,row,index){
                    console.log(row.editing);
                        if (row.editing){
                            var s = '<a href="#" onclick="saverow(this)">Save</a> ';
                            var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';
                            return s+c;
                        } else {
                            var e = '<a href="#" onclick="editrow(this)">Edit</a> ';
                            var d = '<a href="#" onclick="deleterow(this)">Delete</a>';
                            return e+d;
                        }
                    }
                }
            ]],
            onBeforeEdit:function(index,row){
                row.editing = true;
                updateActions(index);
            },
            onAfterEdit:function(index,row){
                row.editing = false;
                updateActions(index);
            },
            onCancelEdit:function(index,row){
                row.editing = false;
                updateActions(index);
            }
        });
    });
    function updateActions(index){
        $('#dg').datagrid('updateRow',{
            index: index,
            row:{}
        });
    }
    function getRowIndex(target){
        var tr = $(target).closest('tr.datagrid-row');
        return parseInt(tr.attr('datagrid-row-index'));
    }
    function editrow(target){
        $('#dg').datagrid('beginEdit', getRowIndex(target));
    }
    function deleterow(target){
        $.messager.confirm('Confirm','Are you sure?',function(r){
            if (r){
                $('#dg').datagrid('deleteRow', getRowIndex(target));
            }
        });
    }
    function saverow(target){
        $('#dg').datagrid('endEdit', getRowIndex(target));
    }
    function cancelrow(target){
        $('#dg').datagrid('cancelEdit', getRowIndex(target));
    }
    function insert(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            var index = $('#dg').datagrid('getRowIndex', row);
        } else {
            index = 0;
        }
        $('#dg').datagrid('insertRow', {
            index: index,
            row:{
                status:'P'
            }
        });
        $('#dg').datagrid('selectRow',index);
        $('#dg').datagrid('beginEdit',index);
    }
</script>

<div style="margin:10px 0">
    <a href="#" class="easyui-linkbutton" onclick="insert()">Insert Row</a>
</div>
<table id="dg"></table>

</body>