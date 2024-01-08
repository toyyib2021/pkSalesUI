package com.pureKnowledge.salesApp.data

import android.util.Log
import com.pureKnowledge.salesApp.model.Activation
import com.pureKnowledge.salesApp.model.Categories
import com.pureKnowledge.salesApp.model.Customer
import com.pureKnowledge.salesApp.model.CustomerType
import com.pureKnowledge.salesApp.model.Order
import com.pureKnowledge.salesApp.model.PaymentPlan
import com.pureKnowledge.salesApp.model.Payment
import com.pureKnowledge.salesApp.model.Product
import com.pureKnowledge.salesApp.model.ProductRecord
import com.pureKnowledge.salesApp.model.ProductType
import com.pureKnowledge.salesApp.model.ProductUnit
import com.pureKnowledge.salesApp.model.SoldProduct
import com.pureKnowledge.salesApp.util.Constants.APP_ID
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.log.LogLevel
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

object SaleDB : SalesRepository {
    private val app = App.create(APP_ID)
    private val user = app.currentUser
    private lateinit var realm: Realm

    init {
        configureTheRealm()
    }

    override fun configureTheRealm() {
        if (user != null) {
            val config = SyncConfiguration.Builder(
                user,
                setOf(ProductType::class, CustomerType::class, Categories::class, ProductUnit::class,
                    Activation::class, PaymentPlan::class, Customer::class, Product::class, Order::class,
                    Payment::class, ProductRecord::class, SoldProduct::class
                    )
            )
                .initialSubscriptions { sub ->
                    add(query = sub.query<ProductType>(query = "owner_id == $0", user.id))
                    add(query = sub.query<CustomerType>(query = "owner_id == $0", user.id))
                    add(query = sub.query<Categories>(query = "owner_id == $0", user.id))
                    add(query = sub.query<ProductUnit>(query = "owner_id == $0", user.id))
                    add(query = sub.query<Activation>(query = "owner_id == $0", user.id))
                    add(query = sub.query<PaymentPlan>(query = "owner_id == $0", "Pk Sale"))
                    add(query = sub.query<Customer>(query = "owner_id == $0", user.id))
                    add(query = sub.query<Product>(query = "owner_id == $0", user.id))
                    add(query = sub.query<Order>(query = "owner_id == $0", user.id))
                    add(query = sub.query<Payment>(query = "owner_id == $0", user.id))
                    add(query = sub.query<ProductRecord>(query = "owner_id == $0", user.id))
                    add(query = sub.query<SoldProduct>(query = "owner_id == $0", user.id))
                }
                .log(LogLevel.ALL)
                .build()
            realm = Realm.open(config)
        }
    }


    // ****** Sold Record ************************************************************ //
    override fun getSoldProduct(): Flow<List<SoldProduct>> {
        return realm.query<SoldProduct>().asFlow().map { it.list }
    }

    override fun filterSoldProductByCustomerID(customerID: String): Flow<List<SoldProduct>> {
        return realm.query<SoldProduct>(query = "customerID CONTAINS[c] $0", customerID)
            .asFlow().map { it.list }
    }

    override fun filterSoldProductByProductId(productId: String): Flow<List<SoldProduct>> {
        return realm.query<SoldProduct>(query = "productId CONTAINS[c] $0", productId)
            .asFlow().map { it.list }
    }

    override suspend fun insertSoldProduct(soldProduct: SoldProduct) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(soldProduct.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertSoldProduct", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateSoldProduct(soldProduct: SoldProduct) {
        realm.write {
            val queriedSoldProduct =
                query<SoldProduct>(query = "_id == $0", soldProduct._id)
                    .first()
                    .find()
            if (queriedSoldProduct != null) {
                queriedSoldProduct.customerID = soldProduct.customerID
                queriedSoldProduct.productId = soldProduct.productId
                queriedSoldProduct.qty = soldProduct.qty
                queriedSoldProduct.returnQty = soldProduct.returnQty
                queriedSoldProduct.orderId = soldProduct.orderId
                queriedSoldProduct.date = soldProduct.date
                queriedSoldProduct.time = soldProduct.time
                queriedSoldProduct.price = soldProduct.price
                queriedSoldProduct.status = soldProduct.status

            } else {
                Log.d("updateSoldProduct", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteSoldProduct(id: ObjectId) {
        realm.write {
            try {
                val soldProduct = query<SoldProduct>(query = "_id == $0", id)
                    .first()
                    .find()
                soldProduct?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteSoldProduct", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllSoldProduct(soldProduct: List<SoldProduct>) {
        realm.write {
            try {
                deleteAll().let { soldProduct }
            } catch (e: Exception) {
                Log.d("deleteAllSoldProduct", "${e.message}")
            }
        }
    }




    // ****** Product Record ************************************************************ //
    override fun getProductRecord(): Flow<List<ProductRecord>> {
        return realm.query<ProductRecord>().asFlow().map { it.list }
    }

    override fun filterProductRecordByProductId(productID: String): Flow<List<ProductRecord>> {
        return realm.query<ProductRecord>(query = "productID CONTAINS[c] $0", productID)
            .asFlow().map { it.list }
    }

    override suspend fun insertProductRecord(productRecord: ProductRecord) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(productRecord.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertProductRecord", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateProductRecord(productRecord: ProductRecord) {
        realm.write {
            val queriedProductRecord =
                query<ProductRecord>(query = "_id == $0", productRecord._id)
                    .first()
                    .find()
            if (queriedProductRecord != null) {
                queriedProductRecord.productID = productRecord.productID
                queriedProductRecord.qty = productRecord.qty
                queriedProductRecord.createDate = productRecord.createDate
                queriedProductRecord.modifiedDate = productRecord.modifiedDate
                queriedProductRecord.recordStatus = productRecord.recordStatus

            } else {
                Log.d("updateProductRecord", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteProductRecord(id: ObjectId) {
        realm.write {
            try {
                val productRecord = query<ProductRecord>(query = "_id == $0", id)
                    .first()
                    .find()
                productRecord?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteProductRecord", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllProductRecord(productRecord: List<ProductRecord>) {
        realm.write {
            try {
                deleteAll().let { productRecord }
            } catch (e: Exception) {
                Log.d("deleteAllProductRecord", "${e.message}")
            }
        }
    }



    // ****** Payment ************************************************************ //
    override fun getPayment(): Flow<List<Payment>> {
        return realm.query<Payment>().asFlow().map { it.list }
    }

    override fun filterPaymentByDate(date: String): Flow<List<Payment>> {
        return realm.query<Payment>(query = "date CONTAINS[c] $0", date)
            .asFlow().map { it.list }
    }

    override fun filterPaymentByCustomerId(customerId: String): Flow<List<Payment>> {
        return realm.query<Payment>(query = "customerId CONTAINS[c] $0", customerId)
            .asFlow().map { it.list }
    }

    override suspend fun insertPayment(payment: Payment) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(payment.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertPayment", e.message.toString())
                }
            }
        }
    }

    override suspend fun updatePayment(payment: Payment) {
        realm.write {
            val queriedPayment =
                query<Payment>(query = "_id == $0", payment._id)
                    .first()
                    .find()
            if (queriedPayment != null) {
                queriedPayment.customerId = payment.customerId
                queriedPayment.amount = payment.amount
                queriedPayment.date = payment.date
                queriedPayment.time = payment.time
                queriedPayment.status = payment.status

            } else {
                Log.d("updatePayment", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deletePayment(id: ObjectId) {
        realm.write {
            try {
                val payment = query<Payment>(query = "_id == $0", id)
                    .first()
                    .find()
                payment?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deletePayment", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllPayment(payment: List<Payment>) {
        realm.write {
            try {
                deleteAll().let { payment }
            } catch (e: Exception) {
                Log.d("deleteAllPayment", "${e.message}")
            }
        }
    }






    // ****** Order ************************************************************ //
    override fun getOrder(): Flow<List<Order>> {
        return realm.query<Order>().asFlow().map { it.list }
    }

    override fun filterOrderByPaidAmount(paidAmount: String): Flow<List<Order>> {
        return realm.query<Order>(query = "paidAmount CONTAINS[c] $0", paidAmount)
            .asFlow().map { it.list }
    }

    override fun filterOrderByCustomerId(customerId: String): Flow<List<Order>> {
        return realm.query<Order>(query = "customerId CONTAINS[c] $0", customerId)
            .asFlow().map { it.list }
    }

    override fun filterOrderByOrderId(orderId: String): Flow<List<Order>> {
        return realm.query<Order>(query = "orderId CONTAINS[c] $0", orderId)
            .asFlow().map { it.list }
    }

    override fun filterOrderByDate(date: String): Flow<List<Order>> {
        return realm.query<Order>(query = "date CONTAINS[c] $0", date)
            .asFlow().map { it.list }
    }

    override suspend fun insertOrder(order: Order) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(order.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertOrder", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateOrder(order: Order) {
        realm.write {
            val queriedOrder =
                query<Order>(query = "_id == $0", order._id)
                    .first()
                    .find()
            if (queriedOrder != null) {
                queriedOrder.customerId = order.customerId
                queriedOrder.orderId = order.orderId
                queriedOrder.discount = order.discount
                queriedOrder.date = order.date
                queriedOrder.totalAmount = order.totalAmount
                queriedOrder.time = order.time
                queriedOrder.paidAmount = order.paidAmount
                queriedOrder.orderStatus = order.orderStatus
            } else {
                Log.d("updateOrder", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteOrder(id: ObjectId) {
        realm.write {
            try {
                val order = query<Order>(query = "_id == $0", id)
                    .first()
                    .find()
                order?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteOrder", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllOrder(order: List<Order>) {
        realm.write {
            try {
                deleteAll().let { order }
            } catch (e: Exception) {
                Log.d("deleteAllOrder", "${e.message}")
            }
        }
    }





    // ****** Product ************************************************************ //
    override fun getProduct(): Flow<List<Product>> {
        return realm.query<Product>().asFlow().map { it.list }
    }

    override fun filterProductByName(productName: String): Flow<List<Product>> {
        return realm.query<Product>(query = "productName CONTAINS[c] $0", productName)
            .asFlow().map { it.list }
    }

    override fun filterProductByCategory(category: String): Flow<List<Product>> {
        return realm.query<Product>(query = "category CONTAINS[c] $0", category)
            .asFlow().map { it.list }
    }

    override fun filterProductByserviceOrProduct(serviceOrProduct: String): Flow<List<Product>> {
        return realm.query<Product>(query = "serviceOrProduct CONTAINS[c] $0", serviceOrProduct)
            .asFlow().map { it.list }
    }

    override suspend fun insertProduct(product: Product) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(product.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertProduct", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateProduct(product: Product) {
        realm.write {
            val queriedProduct =
                query<Product>(query = "_id == $0", product._id)
                    .first()
                    .find()
            if (queriedProduct != null) {
                queriedProduct.productName = product.productName
                queriedProduct.category = product.category
                queriedProduct.type = product.type
                queriedProduct.price = product.price
                queriedProduct.repPrice = product.repPrice
                queriedProduct.discountPrice = product.discountPrice
                queriedProduct.pics = product.pics
                queriedProduct.serviceOrProduct = product.serviceOrProduct

            } else {
                Log.d("updateProduct", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteProduct(id: ObjectId) {
        realm.write {
            try {
                val product = query<Product>(query = "_id == $0", id)
                    .first()
                    .find()
                product?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteProduct", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllProduct(product: List<Product>) {
        realm.write {
            try {
                deleteAll().let { product }
            } catch (e: Exception) {
                Log.d("deleteAllProduct", "${e.message}")
            }
        }
    }




    // ****** Customer ************************************************************ //
    override fun getCustomer(): Flow<List<Customer>> {
        return realm.query<Customer>().asFlow().map { it.list }
    }

    override fun filterCustomerByName(customerName: String): Flow<List<Customer>> {
        return realm.query<Customer>(query = "customerName CONTAINS[c] $0", customerName)
            .asFlow().map { it.list }
    }

    override fun filterCustomerByPhone(customerPhone: String): Flow<List<Customer>> {
        return realm.query<Customer>(query = "phone CONTAINS[c] $0", customerPhone)
            .asFlow().map { it.list }
    }

    override fun filterCustomerByType(customerType: String): Flow<List<Customer>> {
        return realm.query<Customer>(query = "customerType CONTAINS[c] $0", customerType)
            .asFlow().map { it.list }
    }
    override suspend fun insertCustomer(customer: Customer) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(customer.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertCustomer", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateCustomer(customer: Customer) {
        realm.write {
            val queriedCustomer =
                query<Customer>(query = "_id == $0", customer._id)
                    .first()
                    .find()
            if (queriedCustomer != null) {
                queriedCustomer.customerName = customer.customerName
                queriedCustomer.phone = customer.phone
                queriedCustomer.address = customer.address
                queriedCustomer.customerType = customer.customerType

            } else {
                Log.d("updateCustomer", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteCustomer(id: ObjectId) {
        realm.write {
            try {
                val customer = query<Customer>(query = "_id == $0", id)
                    .first()
                    .find()
                customer?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteCustomer", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllCustomer(customer: List<Customer>) {
        realm.write {
            try {
                deleteAll().let { customer }
            } catch (e: Exception) {
                Log.d("deleteAllCustomer", "${e.message}")
            }
        }
    }



    // ****** Payment Plan ************************************************************ //
    override fun getPaymentPlan(): Flow<List<PaymentPlan>> {
        return realm.query<PaymentPlan>().asFlow().map { it.list }
    }

    override fun filterPaymentPlanByName(name: String): Flow<List<PaymentPlan>> {
        return realm.query<PaymentPlan>(query = "name CONTAINS[c] $0", name)
            .asFlow().map { it.list }
    }

    override suspend fun insertPaymentPlan(paymentPlan: PaymentPlan) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(paymentPlan.apply { owner_id = "Pk Sale" })
                } catch (e: Exception) {
                    Log.d("insertPaymentPlan", e.message.toString())
                }
            }
        }
    }

    override suspend fun updatePaymentPlan(paymentPlan: PaymentPlan) {
        realm.write {
            val queriedPaymentPlan =
                query<PaymentPlan>(query = "_id == $0", paymentPlan._id)
                    .first()
                    .find()
            if (queriedPaymentPlan != null) {
                queriedPaymentPlan.saveAmount = paymentPlan.saveAmount
                queriedPaymentPlan.amountPerMonth = paymentPlan.amountPerMonth
                queriedPaymentPlan.planType = paymentPlan.planType
                queriedPaymentPlan.discount = paymentPlan.discount
            } else {
                Log.d("updatePaymentPlan", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deletePaymentPlan(id: ObjectId) {
        realm.write {
            try {
                val paymentPlan = query<PaymentPlan>(query = "_id == $0", id)
                    .first()
                    .find()
                paymentPlan?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deletePaymentPlan", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllPaymentPlan(paymentPlan: List<PaymentPlan>) {
        realm.write {
            try {
                deleteAll().let { paymentPlan }
            } catch (e: Exception) {
                Log.d("deleteAllPaymentPlan", "${e.message}")
            }
        }
    }




    // ****** Activation ************************************************************ //
    override fun getActivation(): Flow<List<Activation>> {
        return realm.query<Activation>().asFlow().map { it.list }
    }

    override fun filterActivationByName(name: String): Flow<List<Activation>> {
        return realm.query<Activation>(query = "name CONTAINS[c] $0", name)
            .asFlow().map { it.list }
    }

    override suspend fun insertActivation(activation: Activation) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(activation.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertActivation", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateActivation(activation: Activation) {
        realm.write {
            val queriedActivation =
                query<Activation>(query = "_id == $0", activation._id)
                    .first()
                    .find()
            if (queriedActivation != null) {
                queriedActivation.companyEmail = activation.companyEmail
                queriedActivation.companyName = activation.companyName
                queriedActivation.pass = activation.pass
                queriedActivation.activationKey = activation.activationKey
                queriedActivation.startDate = activation.startDate
                queriedActivation.endDate = activation.endDate

            } else {
                Log.d("updateActivation", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteActivation(id: ObjectId) {
        realm.write {
            try {
                val activation = query<Activation>(query = "_id == $0", id)
                    .first()
                    .find()
                activation?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteActivation", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllActivation(activation: List<Activation>) {
        realm.write {
            try {
                deleteAll().let { activation }
            } catch (e: Exception) {
                Log.d("deleteAllActivation", "${e.message}")
            }
        }
    }



    // ****** Product Type ************************************************************ //
    override fun getProductTypes(): Flow<List<ProductType>> {
        return realm.query<ProductType>().asFlow().map { it.list }
    }

    override suspend fun insertProductType(productType: ProductType) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(productType.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("MongoRepository", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateProductType(productType: ProductType) {
        realm.write {
            val queriedPerson =
                query<ProductType>(query = "_id == $0", productType._id)
                    .first()
                    .find()
            if (queriedPerson != null) {
                queriedPerson.producttype = productType.producttype
            } else {
                Log.d("updateProductType", "Queried Product does not exist.")
            }
        }
    }

    override suspend fun deleteProductType(id: ObjectId) {
        realm.write {
            try {
                val productType = query<ProductType>(query = "_id == $0", id)
                    .first()
                    .find()
                productType?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteProductType", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllProductType(productType: List<ProductType>) {
        realm.write {
            try {
                deleteAll().let { productType }
            } catch (e: Exception) {
                Log.d("deleteAllProductType", "${e.message}")
            }
        }
    }


    // ****** Customer Type ************************************************************ //
    override fun getCustomerType(): Flow<List<CustomerType>> {
        return realm.query<CustomerType>().asFlow().map { it.list }
    }

    override fun filterCustomerType(name: String): Flow<List<CustomerType>> {
        return realm.query<CustomerType>(query = "name CONTAINS[c] $0", name)
            .asFlow().map { it.list }
    }

    override suspend fun insertCustomerType(customer: CustomerType) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(customer.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertCustomerType", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateCustomerType(customer: CustomerType) {
        realm.write {
            val queriedPerson =
                query<CustomerType>(query = "_id == $0", customer._id)
                    .first()
                    .find()
            if (queriedPerson != null) {
                queriedPerson.customerType = customer.customerType
            } else {
                Log.d("MongoRepository", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteCustomerType(id: ObjectId) {
        realm.write {
            try {
                val person = query<CustomerType>(query = "_id == $0", id)
                    .first()
                    .find()
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepository", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllCustomerType(customerType: List<CustomerType>) {
        realm.write {
            try {
                deleteAll().let { customerType }
            } catch (e: Exception) {
                Log.d("deleteAllProductType", "${e.message}")
            }
        }
    }


    // ****** Categories Type ************************************************************ //
    override fun getCategories(): Flow<List<Categories>> {
        return realm.query<Categories>().asFlow().map { it.list }
    }

    override fun filterCategoriesByName(name: String): Flow<List<Categories>> {
        return realm.query<Categories>(query = "name CONTAINS[c] $0", name)
            .asFlow().map { it.list }
    }

    override suspend fun insertCategories(categories: Categories) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(categories.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertCategories", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateCategories(categories: Categories) {
        realm.write {
            val queriedPerson =
                query<Categories>(query = "_id == $0", categories._id)
                    .first()
                    .find()
            if (queriedPerson != null) {
                queriedPerson.name = categories.name
            } else {
                Log.d("updateCategories", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteCategories(id: ObjectId) {
        realm.write {
            try {
                val categories = query<Categories>(query = "_id == $0", id)
                    .first()
                    .find()
                categories?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteCategories", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllCategories(categories: List<Categories>) {
        realm.write {
            try {
                deleteAll().let { categories }
            } catch (e: Exception) {
                Log.d("deleteAllCategories", "${e.message}")
            }
        }
    }


    // ****** Categories Type ************************************************************ //
    override fun getProductUnit(): Flow<List<ProductUnit>> {
        return realm.query<ProductUnit>().asFlow().map { it.list }
    }

    override fun filterProductUnitByName(name: String): Flow<List<ProductUnit>> {
        return realm.query<ProductUnit>(query = "name CONTAINS[c] $0", name)
            .asFlow().map { it.list }
    }

    override suspend fun insertProductUnit(productUnit: ProductUnit) {
        if (user != null) {
            realm.write {
                try {
                    copyToRealm(productUnit.apply { owner_id = user.id })
                } catch (e: Exception) {
                    Log.d("insertProductUnit", e.message.toString())
                }
            }
        }
    }

    override suspend fun updateProductUnit(productUnit: ProductUnit) {
        realm.write {
            val queriedPerson =
                query<ProductUnit>(query = "_id == $0", productUnit._id)
                    .first()
                    .find()
            if (queriedPerson != null) {
                queriedPerson.productUnit = productUnit.productUnit
            } else {
                Log.d("updateProductUnit", "Queried Customer does not exist.")
            }
        }
    }

    override suspend fun deleteProductUnit(id: ObjectId) {
        realm.write {
            try {
                val productUnit = query<ProductUnit>(query = "_id == $0", id)
                    .first()
                    .find()
                productUnit?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("deleteProductUnit", "${e.message}")
            }
        }
    }

    override suspend fun deleteAllProductUnit(productUnit: List<ProductUnit>) {
        realm.write {
            try {
                deleteAll().let { productUnit }
            } catch (e: Exception) {
                Log.d("deleteAllProductUnit", "${e.message}")
            }
        }
    }

}