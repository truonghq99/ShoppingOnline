var lap = document.getElementsByClassName("for");
const id = document.querySelector(".id");

for (let i = 1; i <= lap.length; i++) {
    const quantity = "quantity" + i;
    const price = "price" + i;
    const amount = "amount" + i;
    const discount = "discount" + i;
    const totalPrice = "totalPrice" + i;
    $("#" + quantity).change(function() {
        const quanti = $("#" + quantity).val();
        const pri = $("#" + price).val();
        console.log(pri);
        var amo = parseFloat(quanti) * parseFloat(pri);
        console.log(amo);
        $("#" + amount).val(amo);
        const dis = $("#" + discount).val();
        var total = amo - amo * parseFloat(dis) / 100;
        $("#" + totalPrice).val(total);
        var sum = 0;
        $('.totalPrice').each(function() {
            sum += parseFloat($(this).val());
        });
        $("#priceBill").val(sum);
        const disBill = $("#discountBill").val();
        var totalB = sum - sum * parseFloat(disBill) / 100;
        $("#totalBill").val(totalB);
        console.log(amount);
    });
    $("#" + discount).change(function() {
        var amo = $("#" + amount).val();
        const dis = $("#" + discount).val();
        var total = parseFloat(amo) - parseFloat(amo) * parseFloat(dis) / 100;
        $("#" + totalPrice).val(total);
        var sum = 0;
        $('.totalPrice').each(function() {
            sum += parseFloat($(this).val());
        });
        $("#priceBill").val(sum);
        const disBill = $("#discountBill").val();
        var totalB = sum - sum * parseFloat(disBill) / 100;
        $("#totalBill").val(totalB);
    });
}

$("#discountBill").change(function() {
    const priceBill = $("#priceBill").val();
    const discountBill = $("#discountBill").val();
    var totalBill = parseFloat(priceBill) - parseFloat(priceBill) * parseFloat(discountBill) / 100;
    $("#totalBill").val(totalBill);
});