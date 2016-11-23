package com.modulus.uno

class FacturaCommand implements MessageCommand {
  DatosDeFacturacion datosDeFacturacion
  Contribuyente emisor
  Contribuyente receptor
  String emitter

  List<Concepto> conceptos
  List<Impuesto> impuestos
  Boolean betweenIntegrated = false
}
