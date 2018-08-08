document.addEventListener('DOMContentLoaded', function () {
    console.log('Document loaded');

    var addCustomerButton = document.querySelector('.add-button');
    var deleteCustomerButtons = document.getElementsByClassName('delete-button');

    function handleAddCustomerClick(event) {
        console.log('handleAddCustomerClick event', event);

        window.location.href = 'add'
    }

    function handleDeleteCustomerClick(event) {
        console.log('handleDeleteCustomerClick event', event);

        if (!confirm('Are you sure you want to delete this customer?')) event.preventDefault();
    }

    // Prevent from adding an event to non-existent elements
    addCustomerButton && addCustomerButton.addEventListener('click', handleAddCustomerClick, false);

    for (var i = 0; i < deleteCustomerButtons.length; i++) {
        deleteCustomerButtons[i].addEventListener('click', handleDeleteCustomerClick, false);
    }
});

