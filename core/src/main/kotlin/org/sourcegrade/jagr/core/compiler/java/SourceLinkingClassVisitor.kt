/*
 *   Jagr - SourceGrade.org
 *   Copyright (C) 2021-2022 Alexander Staeding
 *   Copyright (C) 2021-2022 Contributors
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.sourcegrade.jagr.core.compiler.java

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class SourceLinkingClassVisitor(
    className: String,
) : ClassVisitor(Opcodes.ASM9) {

    private val packageName = with(className) { lastIndexOf('/').let { if (it == -1) null else substring(0, it) } }
    var sourceFileName: String? = null

    override fun visitSource(source: String?, debug: String?) {
        if (source == null || sourceFileName != null) return
        sourceFileName = if (packageName == null) {
            source
        } else {
            "$packageName/$source"
        }
    }
}