$(function() {
				$("#from").datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 2,
					onClose: function(selectedDate) {
						$("#to").datepicker("option", "minDate", selectedDate);
					}
				});
				$("#to").datepicker({
					defaultDate: "+1w",
					changeMonth: true,
					numberOfMonths: 2,
					onClose: function(selectedDate) {
						$("#from").datepicker("option", "maxDate", selectedDate);
					}
				});
				$("#datepicker , #datepicker1").datepicker({
					changeMonth: true,
					changeYear: true
				}); //日期修改年份
				$("#selectAll").click(function() {
					//alert($("#selectAll").attr("checked"));
					$("input[name='item']").each(function() {
						$(this).attr("checked", true);
					});
					if ($("#selectAll").attr("checked")) {
						$("#selectRevsern").attr("checked", false);
					}
				});
				$("#selectRevsern").click(function() {
					if ($("#selectRevsern").attr("checked")) {
						$("input[name='item']").each(function() {
							$(this).attr("checked", !$(this).attr("checked"));
						});
						$("#selectAll").attr("checked", false);
					}
				});
				$("#deleteList").click(function() {
					var ids = [];
					var index = 0;
					$("input[name='item']").each(function() {
						if ($(this).attr("checked")) {
							ids[index] = $(this).parent().next().text();
							index++;
						}
					});
					if (ids.length == 0) {
						alert("没有选中行");
					} else {
						var local = $(this).attr("value");
						window.open(local + ids);
					}
				});
				$(".delete").click(function() {
					var flag = window.confirm("确认删除？");
					var local = $(this).attr("value");
				
					if (flag) {
						window.open(local);
					}
				});
			});