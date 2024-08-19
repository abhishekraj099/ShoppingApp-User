package com.example.shoppingappuser.viewModels


import android.net.Uri
import androidx.lifecycle.viewModelScope


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


import androidx.lifecycle.ViewModel
import com.example.shoppingappuser.common.HomeScreenState
import com.example.shoppingappuser.common.ResultState
import com.example.shoppingappuser.domain.models.CartDataModels
import com.example.shoppingappuser.domain.models.CategoryDataModels
import com.example.shoppingappuser.domain.models.ProductDataModels
import com.example.shoppingappuser.domain.models.UserData
import com.example.shoppingappuser.domain.models.UserDataParent


import com.example.shoppingappuser.domain.use_Case.AddToFavUseCase
import com.example.shoppingappuser.domain.use_Case.AddtoCardUseCase
import com.example.shoppingappuser.domain.use_Case.CreateUserUseCase
import com.example.shoppingappuser.domain.use_Case.GetAllCategoriesUseCase
import com.example.shoppingappuser.domain.use_Case.GetAllFavUseCase
import com.example.shoppingappuser.domain.use_Case.GetAllProductUseCase
import com.example.shoppingappuser.domain.use_Case.GetBannerUseCase
import com.example.shoppingappuser.domain.use_Case.GetCartUseCase
import com.example.shoppingappuser.domain.use_Case.GetCheckOutUseCase
import com.example.shoppingappuser.domain.use_Case.GetSpecifiCategoryItems
import com.example.shoppingappuser.domain.use_Case.GetUserUseCase
import com.example.shoppingappuser.domain.use_Case.LoginUserUseCase
import com.example.shoppingappuser.domain.use_Case.UpDateUserDataUseCase
import com.example.shoppingappuser.domain.use_Case.getCategoryInLimit
import com.example.shoppingappuser.domain.use_Case.getProductByID
import com.example.shoppingappuser.domain.use_Case.getProductsInLimitUseCase
import com.example.shoppingappuser.domain.use_Case.userProfileImageUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine


@HiltViewModel
class ShoppingAppViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val upDateUserDataUseCase: UpDateUserDataUseCase,
    private val userProfileImageUseCase: userProfileImageUseCase,
    private val getCategoryInLimit: getCategoryInLimit,
    private val getProductsInLimitUseCase: getProductsInLimitUseCase,
    private val addtoCardUseCase: AddtoCardUseCase,
    private val getProductByID: getProductByID,
    private val addtoFavUseCase: AddToFavUseCase,
    private val getAllFavUseCase: GetAllFavUseCase,
    private val getAllProductsUseCase: GetAllProductUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val getCheckOutUseCase : GetCheckOutUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getSpecifiCategoryItems: GetSpecifiCategoryItems,

    ) : ViewModel() {

    private val _singUpScreenState = MutableStateFlow(SignUpScreenState())
    val singUpScreenState = _singUpScreenState.asStateFlow()

    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()

    private val _upDateScreenState = MutableStateFlow(UpDateScreenState())
    val upDateScreenState = _upDateScreenState.asStateFlow()

    private val _userProfileImageState = MutableStateFlow(uploadUserProfileImageState())
    val userProfileImageState = _userProfileImageState.asStateFlow()


    //how to use this , when usr click on button
    private val _addToCartState = MutableStateFlow(AddtoCardState())
    val addToCartState = _addToCartState.asStateFlow()

    private val _getProductByIDState = MutableStateFlow(GetProductByIDState())
    val getProductByIDState = _getProductByIDState.asStateFlow()

    //how to use this , when usr click on button
    private val _addtoFavState = MutableStateFlow(AddtoFavState())
    val addtoFavState = _addtoFavState.asStateFlow()

    private val _getAllFavState = MutableStateFlow(GetAllFavState())
    val getAllFavState = _getAllFavState.asStateFlow()

    private val _getAllProductsState = MutableStateFlow(GetAllProductsState())
    val getAllProductsState = _getAllProductsState.asStateFlow()


    private val _getCartState = MutableStateFlow(GetCartsState())
    val getCartState = _getCartState.asStateFlow()


    private val _getAllCategoriesState = MutableStateFlow(GetAllCategoriesState())
    val getAllCategoriesState = _getAllCategoriesState.asStateFlow()



    private val _getCheckOutState = MutableStateFlow(GetCheckOutState())
    val getCheckOutState = _getCheckOutState.asStateFlow()


    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()



    private val _getSpecifiCategoryItemsState = MutableStateFlow(GetSpecifiCategoryItemsState())
    val getSpecifiCategoryItemsState = _getSpecifiCategoryItemsState.asStateFlow()



    fun getSpecifiCategoryItems(
        categoryName : String
    ){
        viewModelScope.launch {
            getSpecifiCategoryItems.getSpecifiCategoryItems(categoryName).collect{
                when(it){
                    is ResultState.Error -> {
                        _getSpecifiCategoryItemsState.value = _getSpecifiCategoryItemsState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getSpecifiCategoryItemsState.value = _getSpecifiCategoryItemsState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getSpecifiCategoryItemsState.value = _getSpecifiCategoryItemsState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }





    fun getCheckOut(productId: String){
        viewModelScope.launch {
            getCheckOutUseCase.getCheckoutUseCse(productId).collect{
                when(it){
                    is ResultState.Error -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getCheckOutState.value = _getCheckOutState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }
    }





    fun getAllCategories(){
        viewModelScope.launch {
            getAllCategoriesUseCase.getAllCategoriesUseCase().collect{
                when(it){
                    is ResultState.Error -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getAllCategoriesState.value = _getAllCategoriesState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }
    }


    fun getCart(){
        viewModelScope.launch {
            getCartUseCase.getCart().collect {
                when(it){
                    is ResultState.Error -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getCartState.value = _getCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }



    fun getAllProducts(){
        viewModelScope.launch {
            getAllProductsUseCase.getAllProduct().collect{
                when(it){
                    is ResultState.Loading -> {
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Error -> {
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Success ->{
                        _getAllProductsState.value = _getAllProductsState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }

        }
    }



    fun getAllFav(){
        viewModelScope.launch {
            getAllFavUseCase.getAllFav().collect{
                when(it){
                    is ResultState.Error -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getAllFavState.value = _getAllFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }



    fun addToFav(productDataModels: ProductDataModels){
        viewModelScope.launch {
            addtoFavUseCase.addtoFav(productDataModels).collect{
                when(it){
                    is ResultState.Error -> {
                        _addtoFavState.value = _addtoFavState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _addtoFavState.value = _addtoFavState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _addtoFavState.value = _addtoFavState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }





    fun getProductByID(productId: String){
        viewModelScope.launch {
            getProductByID.getProductById(productId).collect{
                when(it){
                    is ResultState.Error -> {
                        _getProductByIDState.value = _getProductByIDState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _getProductByIDState.value = _getProductByIDState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _getProductByIDState.value = _getProductByIDState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }

        }
    }


    fun addToCart(
        cartDataModels: CartDataModels
    ){
        viewModelScope.launch {
            addtoCardUseCase.addtoCard( cartDataModels ).collect{
                when(it){
                    is ResultState.Error -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success -> {
                        _addToCartState.value = _addToCartState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }
                }
            }
        }
    }




    init {
        loadHomeScreenData()
    }


//    private val _homeScreenState = MutableStateFlow(HomeScreenState())
//    val homeScreenState = _homeScreenState.asStateFlow()
//


    fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                getCategoryInLimit.getCategoriesInLimited(),
                getProductsInLimitUseCase.getProductsInLimit(),
                getBannerUseCase.getBannerUseCase()
            ) { categoriesResult, productsResult  , bannerResult->
                when {
                    categoriesResult is ResultState.Error ->
                        HomeScreenState(isLoading = false, errorMessage = categoriesResult.message)
                    productsResult is ResultState.Error ->
                        HomeScreenState(isLoading = false, errorMessage = productsResult.message)
                    bannerResult is ResultState.Error ->
                        HomeScreenState(isLoading = false, errorMessage = bannerResult.message)

                    categoriesResult is ResultState.Success &&
                            productsResult is ResultState.Success && bannerResult is ResultState.Success
                    ->
                        HomeScreenState(
                            isLoading = false,
                            categories = categoriesResult.data,
                            products = productsResult.data,
                            banners = bannerResult.data
                        )
                    else -> HomeScreenState(isLoading = true)
                }
            }.collect { state ->
                _homeScreenState.value = state
            }
        }
    }






    fun upLoadUserProfileImage(uri: Uri) {
        viewModelScope.launch {
            userProfileImageUseCase.userProfileImage(uri).collect {
                when (it) {
                    is ResultState.Error -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }


    fun upDateUserData(
        userDataParent: UserDataParent
    ) {
        viewModelScope.launch {
            upDateUserDataUseCase.upDateUserData(userDataParent = userDataParent).collect {
                when (it) {
                    is ResultState.Error -> {
                        _upDateScreenState.value = _upDateScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _upDateScreenState.value = _upDateScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _upDateScreenState.value = _upDateScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }
                }
            }
        }
    }


    fun createUser(userData: UserData) {
        viewModelScope.launch {
            createUserUseCase.createUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _singUpScreenState.value = _singUpScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    ResultState.Loading -> {
                        _singUpScreenState.value = _singUpScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _singUpScreenState.value = _singUpScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }

        }
    }


    fun loginUser(userData: UserData) {
        viewModelScope.launch {
            loginUserUseCase.loginUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    ResultState.Loading -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }

        }
    }


    fun getUserById(uid: String) {
        viewModelScope.launch {
            getUserUseCase.getuserById(uid).collectLatest {
                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )

                    }

                    ResultState.Loading -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }


}

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserDataParent? = null
)


data class SignUpScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class UpDateScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)

data class uploadUserProfileImageState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null
)


data class AddtoCardState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class GetProductByIDState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null

)

data class AddtoFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)


data class GetAllFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels?> = emptyList()

)


data class GetAllProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels?> = emptyList()

)


data class GetCartsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CartDataModels?> = emptyList()

)


data class GetAllCategoriesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CategoryDataModels?> = emptyList()

)


data class GetCheckOutState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null

)

data class GetSpecifiCategoryItemsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels?> = emptyList()

)
