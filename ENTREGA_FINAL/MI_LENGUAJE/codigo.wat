(module
(type $_sig_void (func))
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32 (func (param i32)))
(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(import "runtime" "read" (func $read (result i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0));; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $init)
(func $reserveStack (param $size i32)
   (result i32)
   get_global $MP
   get_global $SP
   set_global $MP
   get_global $SP
   get_local $size
   i32.add
   set_global $SP
   get_global $SP
   get_global $NP
   i32.gt_u
   if
   i32.const 3
call $exception
   end
)
(func $freeStack (type $_sig_void)
   get_global $MP
   i32.load
   i32.load offset=4
   set_global $SP
   get_global $MP
   i32.load
   set_global $MP   
)
(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
   (param $src i32)
   (param $dest i32)
   (param $n i32)
   block
     loop
       get_local $n
       i32.eqz
       br_if 1
       get_local $n
       i32.const 1
       i32.sub
       set_local $n
       get_local $dest
       get_local $src
       i32.load
       i32.store
       get_local $dest
       i32.const 4
       i32.add
       set_local $dest
       get_local $src
       i32.const 4
       i32.add
       set_local $src
       br 0
     end
   end
)
(func $copyndir (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
   (param $src i32)
   (param $dest i32)
   (param $n i32)
   block
     loop
       get_local $n
       i32.eqz
       br_if 1
       get_local $n
       i32.const 1
       i32.sub
       set_local $n
       get_local $dest
       get_local $src
       i32.store
       get_local $dest
       i32.const 4
       i32.add
       set_local $dest
       get_local $src
       i32.const 4
       i32.add
       set_local $src
       br 0
     end
   end
)
(func $suma_coste
	(local $localsStart i32)
   (local $temp i32)
   i32.const 16  ;; let this be the stack size needed (params+locals+2)*4
   call $reserveStack  ;; returns old MP (dynamic link)
   set_local $temp
   get_global $MP
   get_local $temp
   i32.store
   get_global $MP
   get_global $SP
   i32.store offset=4
   get_global $MP
   i32.const 8
   i32.add
   set_local $localsStart
i32.const 0
get_local $localsStart
i32.add	;;p
i32.load
i32.const 0
get_local $localsStart
i32.add	;;p
i32.load
i32.load
i32.const 4
get_local $localsStart
i32.add	;;s
i32.load
i32.add
i32.store
call $freeStack
)
(func $suma_costes
	(result i32)
	(local $localsStart i32)
   (local $temp i32)
   i32.const 52  ;; let this be the stack size needed (params+locals+2)*4
   call $reserveStack  ;; returns old MP (dynamic link)
   set_local $temp
   get_global $MP
   get_local $temp
   i32.store
   get_global $MP
   get_global $SP
   i32.store offset=4
   get_global $MP
   i32.const 8
   i32.add
   set_local $localsStart
i32.const 36
get_local $localsStart
i32.add	;;total
i32.const 0
i32.store
i32.const 40
get_local $localsStart
i32.add	;;i
i32.const 0
i32.store
block
loop
i32.const 40
get_local $localsStart
i32.add	;;i
i32.load
i32.const 32
get_local $localsStart
i32.add	;;tam
i32.load
i32.lt_s
i32.eqz
br_if 1
i32.const 36
get_local $localsStart
i32.add	;;total
i32.const 36
get_local $localsStart
i32.add	;;total
i32.load
i32.const 0
get_local $localsStart
i32.add	;;lista
i32.const 4
i32.const 40
get_local $localsStart
i32.add	;;i
i32.load
i32.mul
i32.add
i32.load
i32.add
i32.store
i32.const 40
get_local $localsStart
i32.add	;;i
i32.const 40
get_local $localsStart
i32.add	;;i
i32.load
i32.const  1
i32.add
i32.store
br 0
end
end
i32.const 36
get_local $localsStart
i32.add	;;total
i32.load
return
call $freeStack
)
(func $init
   (local $localsStart i32)
   (local $temp i32)
   i32.const 60  ;; let this be the stack size needed (params+locals+2)*4
   call $reserveStack  ;; returns old MP (dynamic link)
   set_local $temp
   get_global $MP
   get_local $temp
   i32.store
   get_global $MP
   get_global $SP
   i32.store offset=4
   get_global $MP
   i32.const 8
   i32.add
   set_local $localsStart

i32.const 32
get_local $localsStart
i32.add	;;i
i32.const 0
i32.store
block
loop
i32.const 32
get_local $localsStart
i32.add	;;i
i32.load
i32.const 8
i32.lt_s
i32.eqz
br_if 1
i32.const 36
get_local $localsStart
i32.add	;;p
i32.const 0
i32.add
i32.const 32
get_local $localsStart
i32.add	;;i
i32.load
i32.store
i32.const 36
get_local $localsStart
i32.add	;;p
i32.const 4
i32.add
i32.const 32
get_local $localsStart
i32.add	;;i
i32.load
i32.const  10
i32.mul
i32.store
i32.const 0
get_local $localsStart
i32.add	;;lista
i32.const 4
i32.const 32
get_local $localsStart
i32.add	;;i
i32.load
i32.mul
i32.add
i32.const 36
get_local $localsStart
i32.add	;;p
i32.const 4
i32.add
i32.load
i32.store
i32.const 32
get_local $localsStart
i32.add	;;i
i32.const 32
get_local $localsStart
i32.add	;;i
i32.load
i32.const  1
i32.add
i32.store
br 0
end
end
get_global $SP
i32.const 8
i32.add
i32.const 0
get_local $localsStart
i32.add	;;lista
i32.const 4
i32.const 6
i32.mul
i32.add
i32.store
get_global $SP
i32.const 12
i32.add
i32.const 8
i32.store
call $suma_coste
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.const 0
get_local $localsStart
i32.add	;;lista
get_global $SP
i32.const 8
i32.add
i32.const 8
call $copyn
get_global $SP
i32.const 40
i32.add
i32.const 8
i32.store
call $suma_costes
i32.store
i32.const 48
get_local $localsStart
i32.add	;;bonus
i32.const 5
i32.store
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.load
i32.const 100
i32.gt_s
if
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.load
i32.const  10
i32.const 48
get_local $localsStart
i32.add	;;bonus
i32.load
i32.mul
i32.sub
i32.store
else
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.load
i32.const 48
get_local $localsStart
i32.add	;;bonus
i32.load
i32.const 10
i32.mul
i32.const 5
i32.div_s
i32.sub
i32.store
end
i32.const 44
get_local $localsStart
i32.add	;;coste_total
i32.load
call $print
call $freeStack)
)