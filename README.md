Нужно написать следующие тесты с использованием Java/JUnit/WebDriver 

!!! The page is secured with password: team/interesnee

   1. Check that admin may have access to backend 
   
   http://test.interesnee.ru/admin 
   
   admin@spggoods.com/admin
   2. Check that vendor may have access to backend 
   
   http://test.interesnee.ru/admin 
   
   firstvendor@spggoods.com/vendor
   3. Vendor should not have ability to edit/add/delete products (from interface or passing URLs with ID directly)
   4. Vendor should see only his own orders/products - he should not be able to see products belonging to other
   vendors (try to pass product URL like this one
   http://test.interesnee.ru/admin/products/add?productID=16 to a different vendor. Check that you can't see the data)
   5. Admin should be able to edit and change product name
   6. Vendor should not be able to create new customers by accessing this link http://test.interesnee.ru/admin/customers/add
   7. Check that if Admin is trying to create Empty budget - system performs red validation of Budget Name, Frequency and        Balance    fields.
   
   ￼￼￼
