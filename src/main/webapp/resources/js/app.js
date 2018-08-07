document.addEventListener('DOMContentLoaded', function () {
    console.log('Document loaded');

    var addCustomerButton = document.querySelector('.add-button');

    function handleAddCustomerClick(event) {
        console.log('Event', event);

        window.location.href = 'add'
    }

    // Prevent from adding an event to non-existent element
    addCustomerButton && addCustomerButton.addEventListener('click', handleAddCustomerClick, false);
});

