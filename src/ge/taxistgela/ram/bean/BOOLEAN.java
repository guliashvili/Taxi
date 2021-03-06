/*
 *
 *         Copyright (C) 2015  Giorgi Guliashvili
 *
 *         This program is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         This program is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with this program.  If not, see <http://www.gnu.org/licenses/>
 *
 */

package ge.taxistgela.ram.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by GIO on 6/30/2015.
 */
public class BOOLEAN {
    @Expose
    private boolean b = false;

    public BOOLEAN(boolean b) {
        this.b = b;
    }

    public BOOLEAN(BOOLEAN b) {
        this.b = b.get();
    }

    public boolean get() {
        return b;
    }

    public void set(boolean b) {
        this.b = b;
    }
}
