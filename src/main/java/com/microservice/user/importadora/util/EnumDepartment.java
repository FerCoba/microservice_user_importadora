package com.microservice.user.importadora.util;

public enum EnumDepartment {

	DEP_SISTEMAS(1, "Departamento Sistemas"), DEP_REPUESTOS(2, "Departamento Repuestos"),
	DEP_EXPEDICION(3, "Departamento Expedicion"), DEP_DEPOSITO_FISCAL(4, "Departamento Deposito Fiscal"),
	DEP_ADMINISTRACION(5, "Departamento Administracion"), DEP_CONTADURIA(6, "Departamento Contaduria"),
	DEP_COMERCIO_EXTERIOR(7, "Departamento Comercio Exterior");

	private int code;
	private String description;

	EnumDepartment(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

}
