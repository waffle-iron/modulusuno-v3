class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"api")
        "500"(controller: 'InternalServerError')
        "404"(controller: 'NotFound')
    }
}
