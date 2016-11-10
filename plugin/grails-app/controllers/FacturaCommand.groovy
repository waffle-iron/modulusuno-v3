package com.modulus.uno

class FacturaCommand implements MessageCommand {
  DatosDeFacturacion datosDeFacturacion
  Contribuyente emisor
  Contribuyente receptor

  List<Concepto> conceptos
  List<Impuesto> impuestos
  Boolean betweenIntegrated = false
}
