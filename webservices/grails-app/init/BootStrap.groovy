class BootStrap {

    def grailsApplication

    def init = { servletContext ->
      grailsApplication.getArtefacts("Domain").each{dc->
        //here we register custom marshallers declared in domain classes
        if(dc.hasProperty("marshaller")){
          dc.clazz.marshaller()
        }
      }
    }

    def destroy = {
    }
}
