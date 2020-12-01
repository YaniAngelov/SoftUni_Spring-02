const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
}


const URLS = {
    items: '/api/items',
};

const toString = ({id, name, slot, stamina, strength, attack, defence, owned}) => {
    let columns = `
    <td>${name}</td>
    <td>${slot}</td>
    <td>${stamina}</td>
    <td>${strength}</td>
    <td>${attack}</td>
    <td>${defence}</td>
`
    columns += owned
        ? '<td></td>'
        : `<td>                
        <form class="buy-item-form" action="/api/items/${id}" method="post">
            <button class="btn btn-info">Buy</button>
        </form>
        </td>`
    return `<tr>${columns}</tr>`
};

loader.show();
fetch(URLS.items)
    .then(response => response.json())
    .then(items => {
        let result = '';
        items.forEach(item => {
            const itemString = toString(item);
            result += itemString;
        });
        $('#items-table').html(result);
        loader.hide();
        // document.getElementById('items-table')
        //     .innerHTML = result;

    });

$('#items-table').on('sumbit', 'buy-item-form', function (ev) {
    const url = $(this).attr('action');

    loader.show();
    fetch(url, {method: 'post'})
        .then(data => {
            console.log(data)
            loader.hide();
        });

    ev.preventDefault();
    return false;
});