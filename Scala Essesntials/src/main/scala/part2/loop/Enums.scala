package part2.loop

object Enums {
 enum Permissions {
   case READ, WRITE, EXECUTE, NONE

   //add fields/methods
   def openDocument():Unit = {
     if(this == READ ) println("opening document...")
     else println("reading not allowed" )
   }
  }

 //enum with Constructor Args
 enum PermissionWithBits(bits: Int) {
   case READ extends PermissionWithBits(4) //100
   case WRITE extends PermissionWithBits(2) //010
   case EXECUTE extends PermissionWithBits(1) //001
   case NONE extends PermissionWithBits(0) ///000
 }

 //standard API
 val somePermissionsOrdinal = somePermissions.ordinal
 val allPermissions = PermissionWithBits.values // array of all possible values of the enum
 val readPermission: Permissions = Permissions.valueOf("READ")
 object PermissionWithBits {
   def fromBits(bits: Int):PermissionWithBits = {
     PermissionWithBits.NONE
   }
 }

 val somePermissions: Permissions = Permissions.READ

 def main(args: Array[String]): Unit = {
   somePermissions.openDocument()
 }
}
