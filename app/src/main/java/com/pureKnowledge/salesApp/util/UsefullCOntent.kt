package com.pureKnowledge.salesApp.util

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.teamapt.monnify.sdk.Monnify
import com.teamapt.monnify.sdk.data.model.TransactionDetails
import java.math.BigDecimal

//val monnify = Monnify.instance
//monnify.setApiKey("MK_TEST_5Q2PL98Q70")
//monnify.setContractCode("4582131704")


// monnify.initializePayment(
//  context as Activity,
//  transaction,
//  generateNumericString(6).toInt(),
//  generateAlphallumeric
//  )

val transaction = TransactionDetails.Builder()
    .amount(BigDecimal("2000"))
    .currencyCode("NGN")
    .customerName("Pure Knowledge")
    .customerEmail("toyyibtunde@gmil.com")
    .paymentReference(generateAlphallumeric(22))
    .paymentDescription("Payment for App")
    .build()
//             val context = LocalContext.current


//monnify.setApplicationMode(ApplicationMode.TEST)

//var message by remember { mutableStateOf("no") }
//var generateAlphallumeric by remember { mutableStateOf("") }
//var subscriptionSuccessFull by remember { mutableStateOf(true) }

//                Log.e(TAG, "AccessPinScreen: generateAlphallumeric -> $")
//                // Use rememberLauncherForActivityResult to create a launcher for the MonnifyTransaction contract
//                val monnifyTransactionLauncher = rememberLauncherForActivityResult(
//                    ActivityResultContracts.StartActivityForResult()
//                ) {
//                    // Handle the MonnifyTransactionResponse
//                    val data = it.data
//                    if (data != null){
//                        val monnifyTransactionResponse = data.getParcelableExtra<MonnifyTransactionResponse>(transactionCode.value)
//
//                        message = when (monnifyTransactionResponse?.status) {
//                            Status.PENDING -> "Transaction not paid"
//                            Status.PAID -> "Customer paid exact amount"
//                            Status.OVERPAID -> "Customer paid more than the expected amount."
//                            Status.PARTIALLY_PAID -> "Customer paid less than the expected amount."
//                            Status.FAILED -> "Transaction completed unsuccessfully. This means no payment came in for Account Transfer method or attempt to charge the card failed."
//                            Status.PAYMENT_GATEWAY_ERROR -> "Payment gateway error"
//                            else -> {""}
//                        }
//
//                    } else{
//                        Toast.makeText(context, "data is null", Toast.LENGTH_SHORT).show()
//                    }
//                }