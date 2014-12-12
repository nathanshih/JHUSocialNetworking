// Activated when clicking "Admin" from left navigation panel
$(document).ready(function() {

    // slide out content areas
    $("#contentLeft").slideUp("slow", function() {});
    $("#contentRight").slideUp("slow", function() {
        $("#adminForm").slideDown("slow");
    });

    // Activated when clicking the "Add Student" button on the Admin form
    $("#addStudent").click(function() {
        // first check that the email is in correct format and all fields are filled in
        var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
        if ($("#studentName").val() === '' || $("#studenEmail").val() === '' 
          || $("#studentDegree").val() === '') {
            alert("All fields required.");
        } else if (!($("#studentEmail").val()).match(email)) {
            alert("Invalid email entered.");
        } else {
            $.ajax({
                url: "register",
                contentType: "application/json",
                data: JSON.stringify({
                    "name": $("#studentName").val(),
                    "email": $("#studentEmail").val(),
                    "discipline": $("#studentDegree").val()
                }),
                dataType: "json",
                type: "POST",
                error: function() {
                    alert("Unable to add student.");
                },
                success: function() {
                    alert("Student added successfully.");
                }
            });

            // slide out adminForm
            $("#adminForm").slideUp("slow", function() {
                $("#contentLeft").slideDown("slow");
                $("#contentRight").slideDown("slow");
            });
        }
    });
    
    // Activated when clicking the "Add Course" button on the Admin form
    $("#addCourse").click(function() {
        $.ajax({
            url: "insertCourse",
            contentType: "application/json",
            data: JSON.stringify({
                "courseId": $("#courseId").val(),
                "courseName": $("#courseName").val(),
                "description": $("#courseDescription").val()
            }),
            dataType: "json",
            type: "POST",
            error: function() {
                alert("Unable to add course.");
            },
            success: function() {
                alert("Course added successfully.");
            }
        });

        // slide out adminForm
        $("#adminForm").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
    });
});