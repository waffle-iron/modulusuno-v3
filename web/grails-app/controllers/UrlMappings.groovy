class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
        // apply constraints here
      }
    }

    "/activation"(controller: "recovery") {
      action = [GET : "activeAccountOfLegalRepresentative"]
    }

    "/"(controller:"home")
    "500"(controller: "error", action: "serverError", exception: RuntimeException)
    "404"(view:'/notFound')
  }

}
