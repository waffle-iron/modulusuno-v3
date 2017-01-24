databaseChangeLog = {
  changeSet(author: "cggg88jorge (manual)", id: "adding-list-banks") {
    grailsChange {
      change {
        [[bankingCode:"2001",name:"BANXICO"],
        [bankingCode:"37006",name:"BANCOMEXT"],
        [bankingCode:"37009",name:"BANOBRAS"],
        [bankingCode:"37019",name:"BANJERCITO"],
        [bankingCode:"37135",name:"NAFIN"],
        [bankingCode:"37166",name:"BANSEFI"],
        [bankingCode:"37168",name:"HIPOTECARIA FED"],
        [bankingCode:"40002",name:"BANAMEX"],
        [bankingCode:"40012",name:"BBVA BANCOMER"],
        [bankingCode:"40014",name:"SANTANDER"],
        [bankingCode:"40021",name:"HSBC"],
        [bankingCode:"40030",name:"BAJIO"],
        [bankingCode:"40032",name:"IXE"],
        [bankingCode:"40036",name:"INBURSA"],
        [bankingCode:"40037",name:"INTERACCIONES"],
        [bankingCode:"40042",name:"MIFEL"],
        [bankingCode:"40044",name:"SCOTIABANK"],
        [bankingCode:"40058",name:"BANREGIO"],
        [bankingCode:"40059",name:"INVEX"],
        [bankingCode:"40060",name:"BANSI"],
        [bankingCode:"40062",name:"AFIRME"],
        [bankingCode:"40072",name:"BANORTE"],
        [bankingCode:"40102",name:"INVESTA BANK"],
        [bankingCode:"40103",name:"AMERICAN EXPRES"],
        [bankingCode:"40106",name:"BAMSA"],
        [bankingCode:"40108",name:"TOKYO"],
        [bankingCode:"40110",name:"JP MORGAN"],
        [bankingCode:"40112",name:"BMONEX"],
        [bankingCode:"40113",name:"VE POR MAS"],
        [bankingCode:"40124",name:"DEUTSCHE"],
        [bankingCode:"40126",name:"CREDIT SUISSE"],
        [bankingCode:"40127",name:"AZTECA"],
        [bankingCode:"40128",name:"AUTOFIN"],
        [bankingCode:"40129",name:"BARCLAYS"],
        [bankingCode:"40130",name:"COMPARTAMOS"],
        [bankingCode:"40131",name:"BANCO FAMSA"],
        [bankingCode:"40132",name:"BMULTIVA"],
        [bankingCode:"40133",name:"ACTINVER"],
        [bankingCode:"40134",name:"WAL-MART"],
        [bankingCode:"40136",name:"INTERBANCO"],
        [bankingCode:"40137",name:"BANCOPPEL"],
        [bankingCode:"40138",name:"ABC CAPITAL"],
        [bankingCode:"40139",name:"UBS BANK"],
        [bankingCode:"40140",name:"CONSUBANCO"],
        [bankingCode:"40141",name:"VOLKSWAGEN"],
        [bankingCode:"40143",name:"CIBANCO"],
        [bankingCode:"40145",name:"BBASE"],
        [bankingCode:"40146",name:"BICENTENARIO"],
        [bankingCode:"40147",name:"AGROFINANZAS"],
        [bankingCode:"40148",name:"PAGATODO"],
        [bankingCode:"40150",name:"INMOBILIARIO"],
        [bankingCode:"40151",name:"DONDE"],
        [bankingCode:"40152",name:"BANCREA"],
        [bankingCode:"90600",name:"MONEXCB"],
        [bankingCode:"90601",name:"GBM"],
        [bankingCode:"90602",name:"MASARI"],
        [bankingCode:"90605",name:"VALUE"],
        [bankingCode:"90606",name:"ESTRUCTURADORES"],
        [bankingCode:"90607",name:"TIBER"],
        [bankingCode:"90608",name:"VECTOR"],
        [bankingCode:"90610",name:"B&B"],
        [bankingCode:"90614",name:"ACCIVAL"],
        [bankingCode:"90615",name:"MERRILL LYNCH"],
        [bankingCode:"90616",name:"FINAMEX"],
        [bankingCode:"90617",name:"VALMEX"],
        [bankingCode:"90618",name:"UNICA"],
        [bankingCode:"90619",name:"MAPFRE"],
        [bankingCode:"90620",name:"PROFUTURO"],
        [bankingCode:"90621",name:"CB ACTINVER"],
        [bankingCode:"90622",name:"OACTIN"],
        [bankingCode:"90623",name:"SKANDIA"],
        [bankingCode:"90626",name:"CBDEUTSCHE"],
        [bankingCode:"90627",name:"ZURICH"],
        [bankingCode:"90628",name:"ZURICHVI"],
        [bankingCode:"90630",name:"CB INTERCAM"],
        [bankingCode:"90631",name:"CI BOLSA"],
        [bankingCode:"90634",name:"FINCOMUN"],
        [bankingCode:"90636",name:"HDI SEGUROS"],
        [bankingCode:"90637",name:"ORDER"],
        [bankingCode:"90638",name:"AKALA"],
        [bankingCode:"90640",name:"CB JPMORGAN"],
        [bankingCode:"90642",name:"REFORMA"],
        [bankingCode:"90646",name:"STP"],
        [bankingCode:"90647",name:"TELECOMM"],
        [bankingCode:"90648",name:"EVERCORE"],
        [bankingCode:"90649",name:"OSKNDIA"],
        [bankingCode:"90651",name:"SEGMTY"],
        [bankingCode:"90652",name:"ASEA"],
        [bankingCode:"90653",name:"KUSPIT"],
        [bankingCode:"90655",name:"SOFIEXPRESS"],
        [bankingCode:"90656",name:"UNAGRA"],
        [bankingCode:"90659",name:"ASP INTEGRA OPC"],
        [bankingCode:"90670",name:"LIBERTAD"],
        [bankingCode:"90671",name:"HUASTECAS"],
        [bankingCode:"90673",name:"GNP"],
        [bankingCode:"90674",name:"AXA"],
        [bankingCode:"90675",name:"FICREA"],
        [bankingCode:"90677",name:"CAJA POP MEXICA"],
        [bankingCode:"90678",name:"SURA"],
        [bankingCode:"90901",name:"CLS"],
        [bankingCode:"90902",name:"INDEVAL"]].each { bank ->
          sql.execute("INSERT INTO bank(version,banking_code,name) VALUES (0,${bank.bankingCode},${bank.name})")
        }
      }
    }
  }
}