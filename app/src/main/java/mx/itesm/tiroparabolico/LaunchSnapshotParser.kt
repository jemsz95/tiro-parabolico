package mx.itesm.tiroparabolico

import com.firebase.ui.database.ClassSnapshotParser
import com.google.firebase.database.DataSnapshot

/**
 * Autor: Racket
 * Creación: 28 de Noviembre 2017
 * Última modificación: 28 de Noviembre 2017
 * Descipción: Inserta id a la estructura de datos al momento de mapear
 * la base de datos a la clase local
 */

class LaunchSnapshotParser : ClassSnapshotParser<Launch>(Launch::class.java) {

    override fun parseSnapshot(snapshot: DataSnapshot): Launch {
        val l = super.parseSnapshot(snapshot)
        l.id = snapshot.key
        return l
    }
}
