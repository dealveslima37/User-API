let name = document.querySelector("#name")

function findById() {

	fetch('users')
		.then(response => response.json())
		.then(response => name.innerHTML = response.name)
}

findById()