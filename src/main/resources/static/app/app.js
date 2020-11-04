// Archivo en JavaScript
angular.module('mainModule', []).controller('mainController', function($http, $scope, $window){

      $scope.start = () => {
       $('#btn-start').prop('disabled', true);
             // Invocación del método start del restcontroller
               $http.get('/start').then((response) => {

                    //Ocultar alerta
                    $window.alert(response.data.message);
                    //$('#btn-').prop('disable', false);
                    $('#btn-start').prop('disable', false);
               }).catch((error) => {

                   $window.alert(error.data.message);
               });

       }



      $scope.refresh = () => {

       $('#btn-start').prop('disabled', true);
       $('#btn-refresh').prop('disabled', true);
             // Invocación del método start del restcontroller
               $http.get('/refresh').then((response) => {
                    //Ocultar alerta
                    $window.alert(response.data.message);
                    $('#btn-refresh').prop('disable', false);
                    $('#btn-start').prop('disable', false);
               }).catch((error) => {
                   $window.alert(error.data.message);
               });

       }



   $scope.update = () => {
        // alerta de actualizando
        $('#btn-update').prop('disabled', true);
        $('#alerta-actualizando').show();

        // Invocación del método update del restcontroller
        $http.get('/update').then((response) => {
            //Ocultar alerta de actualizacion
            $window.alert(response.data.message);
            $('#alert-actualizando').hide();
            $('#btn-update').prop('disable', false);

        }).catch((error) => {

            $window.alert(error.data.message);

        });

    }



    $scope.stop = () => {
        //habilitar boton de detener
        $('btn-stop').prop('disabled', true);
        //Alerta de detener servicios
        $http.get('/stop').then((response) => {
            $window.alert(response.data.message);
            $('btn-stop').prop('disabled', false);
        });
    }

})