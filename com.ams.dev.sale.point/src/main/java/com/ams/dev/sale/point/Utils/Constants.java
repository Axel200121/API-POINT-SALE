package com.ams.dev.sale.point.Utils;

public enum Constants {

    EXISTING_ROLE("El rol ya existe en la base de datos"),
    UPDATE_ROLE("El rol actualizado Exitosamente"),
    EMPTY_FIELDS("Faltaron campos por informar, verifca tus datos"),
    ROLE_CREATING("Rol creado Exitosamente"),
    NO_EXISTING_ROLE("No se encontro en la BD este rol"),
    NO_LIST_EXISTING_ROLE("No hay roles registrados en la BD"),
    GET_ROLE("Se encontro información del rol"),
    EXISTING_LIST_ROLE("Lista de roles del sistema"),
    DELETE_ROLE("Rol eliminado exitosamente"),
    ;

    private String value;

    Constants(String value){this.value = value;}

    public String getValue(){return  value;}
}
