function submitForm(e){
e.preventDefault();
       const url = '/odontologos/matricula/' + document.querySelector('#numero').value;
            const settings = {
              method: 'GET'
            }
            fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                          let odontologos = data;
                            document.querySelector('#odontologo_id').value = odontologos.id;
                            document.querySelector('#nombre').value = odontologos.nombre;
                            document.querySelector('#apellido').value = odontologos.apellido;
                            document.querySelector('#matricula').value = odontologos.matricula;
                            //el formulario por default esta oculto y al editar se habilita
                            document.querySelector('#div_odontologo_updating').style.display = "block";
                      }).catch(error => {
                          alert("Error: " + error);

                  //por cada pelicula creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                  //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                  //de llamar a la API para eliminar una pelicula
                  let deleteButton = '<button' +
                                            ' id=' + '\"' + 'btn_delete_' + odontologos.id + '\"' +
                                            ' type="button" onclick="deleteBy('+odontologos.id+')" class="btn rounded-circle btn-danger btn_delete">' +
                                            '&times' +
                                            '</button>';

                  //por cada pelicula creamos un boton que muestra el id y que al hacerle clic invocará
                  //a la función de java script findBy que se encargará de buscar la pelicula que queremos
                  //modificar y mostrar los datos de la misma en un formulario.
                  let updateButton = '<button' +
                                            ' id=' + '\"' + 'btn_id_' + odontologos.id + '\"' +
                                            ' type="button" onclick="findBy('+odontologos.id+')" class="btn btn-primary rounded-circle btn-success btn_id">' +
                                            odontologos.id +
                                            '</button>';

                  //armamos cada columna de la fila
                  //como primer columna pondremos el boton modificar
                  //luego los datos de la pelicula
                  //como ultima columna el boton eliminar
                  odontologoRow.innerHTML = '<td>' + updateButton + '</td>' +
                          '<td class=\"td_nombre\">' + odontologos.nombre.toUpperCase() + '</td>' +
                          '<td class=\"td_apellido\">' + odontologos.apellido.toUpperCase() + '</td>' +
                          '<td>' + deleteButton + '</td>';
});
}

const form = document.getElementById("find_by_matricula");

form.addEventListener("submit", submitForm);
