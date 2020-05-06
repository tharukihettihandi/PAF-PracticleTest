		$(document).ready(function() {
			
			$("#alertSuccess").hide();
			$("#alertError").hide();
	});
	
	// SAVE ============================================
	$(document).on("click","#btnSave",function(event) 
			{
		
		
		// Clear status messages---------------------
		$("#alertSuccess").text("");
		$("#alertSuccess").hide();
		$("#alertError").text("");
		$("#alertError").hide();
		
	
		// Form validation-------------------
		
		var status = validateHospitalForm();
		
	   
		// If not valid
		
		if (status != true) 
		{
			$("#alertError").text(status);
			$("#alertError").show();
			return;
		}
	
	
		// If valid
		var type = ($("#hidhosIDSave").val() == "") ? "POST" : "PUT";
	
		$.ajax(
		{
			url : "HospitalAPI",
			type :type,
			data :$("#formHospital").serialize(),
			dataType :"text",
			complete :function(response, status)
			{
				onHospitalSave1Complete(response.responseText, status);
			}
		});
	
	});	
	function onHospitalSave1Complete(response, status) {
		
		if(status == "success") 
		{
			var resultSet = JSON.parse(response); 
			
			if(resultSet.status.trim() == "success")
			{
				$("#alertSuccess").text("Successfully saved.");
				$("#alertSuccess").show();
				
				
				$("#divHospitalGrid").html(resultSet.data);
				}
			
			elseif (resultSet.status.trim() == "error")
			{
				$("#alertError").text(resultSet.data); 
				$("#alertError").show();
				}
		}
		else if(status == "error") 
		{
			$("#alertError").text("Error while saving.");
			$("#alertError").show();
			}
		else
		{
			$("#alertError").text("Unknown error while saving..");
			$("#alertError").show();
			
		}
		
		$("#hidhosIDSave").val("");
		$("#formHospital")[0].reset(); 
		
	} 
	
 // UPDATE==========================================
	$(document).on("click",".btnUpdate",function(event) 
			{
			$("#hidhosIDSave").val($(this).closest("tr").find('#hidhosIDUpdate').val());
			$("#hosRegNo").val($(this).closest("tr").find('td:eq(0)').text());
			$("#hosName").val($(this).closest("tr").find('td:eq(1)').text());
			$("#hosAddress").val($(this).closest("tr").find('td:eq(2)').text());
			$("#hosPhone").val($(this).closest("tr").find('td:eq(3)').text());
	        $("#hosEmail").val($(this).closest("tr").find('td:eq(4)').text());
			$("#Departments").val($(this).closest("tr").find('td:eq(5)').text());
});	
	

 // Remove==========================================
	$(document).on("click",".btnRemove",function(event) {
		$.ajax(
			{
			url : "HospitalAPI",
			type : "DELETE",
			data : "hosID="+$(this).data("hosid"),
			dataType : "text",
			complete : function(response, status) {
			
				onHospitalDeleteComplete(response.responseText, status);
			}
		});
	});
	
	function onHospitalDeleteComplete(response, status) {
		if (status == "success") {
			var resultSet = JSON.parse(response);
	
			if (resultSet.status.trim() == "success") {
				
				$("#alertSuccess").text("Successfully deleted.");
				$("#alertSuccess").show();
	
				$("#divHospitalGrid").html(resultSet.data);
			} 
			else if (resultSet.status.trim() == "error") 
			{
				$("#alertError").text(resultSet.data);
				$("#alertError").show();
			}
		}
		else if (status == "error")
		{
			$("#alertError").text("Error while deleting.");
			$("#alertError").show();
		} 
		else {
			$("#alertError").text("Unknown error while deleting..");
			$("#alertError").show();
		}	
	}
	// CLIENT-MODEL============================================
    function validateHospitalForm() {

    	// RegNo-------------------------------
    		if ($("#hosRegNo").val().trim() == "") {
    			return "Insert Registration Number.";
    		}
    		// NAME-----------------------------
    		if ($("#hosName").val().trim() == "") {
    			return "Insert hospital Name.";
    		}

    		// Address------------------------------
    		if ($("#hosAddress").val().trim() == "") {
    			return "Insert Address.";

    		}   
    		// Phone-------------------------------
    		if ($("#hosPhone").val().trim() == "") {
    			return "Insert Phone.";

    		}

    		// is numerical value
    		var tmpPhone = $("#hosPhone").val().trim();
    		if (!$.isNumeric(tmpPhone)) {
    			return "Insert a numerical value for Phone.";
    		}

    		// Email-----------------------------
    		if ($("#hosEmail").val().trim() == "") {
    			return "Insert hospital Email.";
    		}

    		// Departments-----------------------------
    		if ($("#Departments").val().trim() == "") {
    			return "Insert Departments.";
    			}	
    				return true;
    			}
	
  