package com.microservice.user.importadora.util;

public enum EnumPrivilege {

	PRIVILEGE_ADMINISTRADOR(1, "Administrador"), PRIVILEGE_RESPONSABLE_VENTAS(2, "Responsable de Ventas"),
	PRIVILEGE_RESPONSABLE_DEP_FISCAL(3, "Responsable Deposito Fiscal"),
	PRIVILEGE_RESPONSABLE_EXPEDICION(4, "Responsable Expedicion"),
	PRIVILEGE_EMPLEADO_EXPEDICION(5, "Empleado Expedicion"),
	PRIVILEGE_RESPONSABLE_REPUESTOS(6, "Responsable Repuestos"), PRIVILEGE_EMPLEADO_REPUESTOS(7, "Empleado Repuestos"),
	PRIVILEGE_RESPONSABLE_CONTADURIA(8, "Responsable Contaduria"),
	PRIVILEGE_EMPLEADO_CONTADURIA(9, "Empleado Contaduria"),
	PRIVILEGE_EMPLEADO_DEP_FISCAL(10, "Empleado Deposito Fiscal"), PRIVILEGE_SISTEMAS(11, "Responsable Sistemas"),
	PRIVILEGE_RESPONSABLE_COMERCIO_EXTERIOR(12, "Responsable Comercio Exterior"),
	PRIVILEGE_EMPLEADO_COMERCIO_EXTERIOR(13, "Empleado Comercio Exterior");

	private int code;
	private String description;

	private EnumPrivilege(int code, String description) {
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
