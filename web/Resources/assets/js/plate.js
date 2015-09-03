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

/**
 * Created by Ratmach on 24/6/15.
 */
/**
 * send plate text
 * @param canvas temporary canvas object
 * @param plate plate text
 * @returns canvas in order to fetch image do canvas.toDataURL()
 */
function getPlate(canvas,plate) {
    var img = new Image;
    var ctx = canvas.getContext('2d');
    img.onload = function () {
        ctx.drawImage(img, 0, 0);
        ctx.font = "bold 32px Arial";
        ctx.fillText(plate, 30, 35);
    };
    img.src = "../images/plate.png";
    return canvas;
}
