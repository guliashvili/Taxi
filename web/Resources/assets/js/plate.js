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
