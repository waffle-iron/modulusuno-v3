import com.modulus.uno.StpDepositStatus
import java.text.SimpleDateFormat

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")

databaseChangeLog = {

    changeSet(author: "tim (generated)", id: "1485920210499-1") {
        addColumn(tableName: "stp_deposit") {
            column(name: "date_created", type: "datetime", defaultValue: sdf.format(new Date())) {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "tim (generated)", id: "1485920210499-2") {
        addColumn(tableName: "stp_deposit") {
            column(name: "last_updated", type: "datetime", defaultValue: sdf.format(new Date())) {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "tim (generated)", id: "1485920210499-3") {
        addColumn(tableName: "stp_deposit") {
            column(name: "status", type: "varchar(255)", defaultValue: StpDepositStatus.RECIBIDO) {
                constraints(nullable: "false")
            }
        }
    }

}
